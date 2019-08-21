package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @date: 2018/12/11 15:12
 * @description: 字符串匹配之大名鼎鼎的 KMP算法
 */
public class KMP_Algorithm {
    /**
     * a, b 分别是主串和模式串；n, m 分别是主串和模式串的长度。
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            // 一直找到 a[i] 和 b[j]     也就是一直找到坏字符，i和j即都是坏字符下标
            while (j > 0 && a[i] != b[j]) {
                /**  这里因为next下标是坏字符之前的前缀的 前缀和后缀 最大匹配的末尾字符的下标，加1即变成了末尾下标的前一位
                  然后继续循环，看此下标的字符是否与坏字符相等，如果相等，即向前继续找下一个
                  如果一直不相等，最终j会变成0，即相当于字符串直接向后移动了m位
                  但也从这看出了没有比BM算法高效，循环过长       */
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * b 表示模式串，m 表示模式串的长度
     */
    private static int[] getNexts(char[] b, int m) {
        /**  核心思想为，利用已经算出的  next[0] next[1] next[2] ...  的结果求 next[i]
          因为 next[i]的结果集可以分解成是 next[i-1]的最后的匹配前缀子串的末尾坐标K，坐标加1后和当前的b[i]对比，
          如果它们相等，那么next[i]的最长前缀子串匹配成功，嗯，next[i]即等于k+1
          如果匹配不上，则退后求next[i-1]的倒数第二个匹配前缀子串的末尾坐标K1,同上，用K1加1后和当前的b[i]比较
          如果第二个也没匹配成功，怎么办，继续求倒数第三个的匹配前缀子串的末尾坐标加1咯，继续不断循环。。。直到k=-1
          怎么求，很有技巧，可以看做是求 如果next[i-1]的最长前缀的末尾坐标K加1匹配不上，则可以看做k的最长前缀匹配，即next[k],
          如果next[k]的值+1也没匹配成功，又可以看做求next[next[k]]的最长前缀子串，还找不到，继续如此循环咯 */
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "ad ad asd naskjd nakjd naskjd naksd nkas nkad nkan and asj d";
        String s2 = "asd";
        int i = kmp(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length());
        System.out.println(i);
    }


}
