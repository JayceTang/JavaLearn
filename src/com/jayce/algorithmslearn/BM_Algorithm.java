package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @date: 2018/12/8 11:51
 * @description:    字符串匹配之  BM算法
 *       定义  主串（代表被匹配的字符串）  模式串（代表要匹配的字符串）
 *                例     "abcabdsd"  主串
 *                       "sd"        模式串         在主串中找模式串，返回找到的第一个匹配的字符串在主串中的首下标
 *      核心思想是利用坏字符规则和好后缀规则
 *      在判断是使用坏字符规则还是好后缀规则时，判断哪个把模式串往后移动的步数比较多，就用哪一个，这样能避免出现使用坏字符在最坏情况下往后退的情况
 *      1.坏字符规则
 *          使用散列表
 *          先用一个bc数组保存模式串中的各个字符最后出现的下标（模式串数组的下标），bc数组的下标则为该字符对应的ascii值
 *      2.好后缀规则  (好后缀麻烦在预处理的理解)
 *            好后缀规则其实可以说只用到了模式串本身，最后也只是用到了坏字符的下标
 *           先拿模式串根据字符串长度K从后往前对比，i~m-2 和 m-1 (m为模式串长度，i为模式串起始位置)，
 *                         寻找模式串的前缀子串和后缀子串中的公共子串数量
 *          使用两个数组，一个int数组以公共字符串的长度为下标，存储公共字符串的起始位置，记为suffix，默认为-1，
 *                  另一个Boolean数组也是以公共字符串的长度为下标，存储该长度的公共字符串是否存在，存在即为true，默认false
 */
public class BM_Algorithm {
    private static final int SIZE = 256;

    /**  坏字符规则   b为模式串， m为模式串的长度， bc为保存模式串中的字符最后出现下标的数组  */
    public void generateBC(char[] b, int m, int[] bc) {
        for (int i=0; i<SIZE; i++) {
            //  赋个初始值,记为-1是为了坏字符在模式串中不存在时模式串向后移动m个位置
            bc[i] = -1;
        }
        for (int i=0; i<m; i++) {
            int ascii = (int)b[i];
            bc[ascii] = i;
        }
    }

    public int bm(char[] a, int n, char[] b, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        // 构建坏字符哈希表
        generateBC(b, m, bc);
        //  记录该长度的公共字符串在模式串中的起始位置，下标即为公共字符串的长度      （PS:这些搞算法的是无所不用其及，我喜欢。。。下标也不会放过）
        int[] suffix = new int[m];
        //  记录该长度的公共字符串是否存在，下标即为公共字符串的长度
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);

        // i 表示主串与模式串对齐的第一个字符
        int i=0;
        while (i <= n-m) {
            int j;
            // 模式串从后往前匹配
            for (j=m-1; j>=0; j--) {
                //  判断主串上该字符和模式串上的该字符是否一致，不一致则退出迭代
                if (a[i+j] != b[j]) {
                    // 迭代退出时，坏字符对应模式串中的下标是 j
                    break;
                }
            }
            if (j < 0) {
                // 匹配成功，返回主串与模式串第一个匹配的字符的位置
                return i;
            }

            // 这里等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
            int x = j - bc[a[i+j]];
            int y = 0;

            if (j < m-1) {
                y = moveByGs(j, m, suffix, prefix);
            }

            i = i + Math.max(x, y);
        }

        return -1;
    }

    private int moveByGs(int j, int m, int[] suffix, boolean[] prefix) {
        //  好后缀长度
        int k = m - 1 - j;
        //  suffix[k]这个数应该只能是0，因为如果不为-1他只可能是0.。。可能他是为了好理解吧，还有下面的  == true， 哈哈嗝
        if (suffix[k] != -1) {
            //  应该直接为  j+1
            return j - suffix[k] + 1;
        }
        //  既然j+1不是，那么就从j+2开始了
        for (int r=j+2; r<=m-1; ++r) {
            if (prefix[m - r]) {
                return r;
            }
        }
        return m;
    }

    /** 好后缀规则
     *  b 表示模式串，m 表示长度，suffix，prefix 数组事先申请好了  */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        // b[0, i]
        for (int i = 0; i < m - 1; ++i) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            // 与 b[0, m-1] 求公共后缀子串      从后往前对比
            while (j >= 0 && b[j] == b[m-1-k]) {
                --j;
                ++k;
            }
            //j+1 表示公共后缀子串在 b[0, i] 中的起始下标
            if (k != 0) {
                suffix[k] = j+1;
            }
            // 如果公共后缀子串也是模式串的前缀子串
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "asdaskjdn askldnas kjnasd asd naskjdn akjsd naskjnfnsdk nak kas jand asnd akjd aasd ";
        String s3 = " as sasdfe nak";
        String s2 = "sdk ";
        BM_Algorithm bm = new BM_Algorithm();
        int bm1 = bm.bm(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length());
        System.out.println(bm1);
    }


}
