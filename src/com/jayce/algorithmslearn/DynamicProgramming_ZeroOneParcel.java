package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @date: 2019/1/10 11:18
 * @description:    每种状态又包含两种，放入不放入，第一个不放入，第二个放入，当时回溯就是没理解这里烧了半天的脑  - -
 */
public class DynamicProgramming_ZeroOneParcel {
    /** weight: 物品重量，n: 物品个数，w: 背包可承载重量     */
    public static int knapsack(int[] weight, int n, int w) {
        // 默认值 false            w+1是背包重量，加1是有一个0的重量在里面
        boolean[][] states = new boolean[n][w+1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;
        states[0][weight[0]] = true;
        // 动态规划状态转移
        for (int i = 1; i < n; ++i) {
            // 不把第 i 个物品放入背包
            for (int j = 0; j <= w; ++j) {
                if (states[i-1][j] == true) {
                    states[i][j] = states[i-1][j];
                }
            }
            // 把第 i 个物品放入背包
            for (int j = 0; j <= w-weight[i]; ++j) {
                if (states[i-1][j]==true) {
                    states[i][j+weight[i]] = true;
                }
            }
        }
        // 输出结果
        for (int i = w; i >= 0; --i) {
            if (states[n-1][i] == true) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 第一个方法是空间换时间，动态规划转移的过程，都是用一个二维数组来实现的，而现在只用了一个一维数组。
     *
     * 注意看55行代码，j需要从大到小处理。如果按从小到大来处理，会出现for循环重复计算的问题。
     */
    public static int knapsack2(int[] items, int n, int w) {
        // 默认值 false
        boolean[] states = new boolean[w+1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0] = true;
        states[items[0]] = true;
        // 动态规划
        for (int i = 1; i < n; ++i) {
            // 把第 i 个物品放入背包
            for (int j = w-items[i]; j >= 0; --j) {
                if (states[j]==true) {
                    states[j+items[i]] = true;
                }
            }
        }
        // 输出结果
        for (int i = w; i >= 0; --i) {
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] weight = {1, 2, 2, 3, 4, 5};
        System.out.println(knapsack(weight, weight.length, 10));
    }

}
