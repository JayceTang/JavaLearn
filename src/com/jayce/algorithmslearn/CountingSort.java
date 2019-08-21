package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName CountingSort
 * @description  计数排序     (王争理解为属于桶排序的一种特殊运用)
 *          实现对数据有要求
 *      假设现在是对1万个学生的分数进行排序，初始数组则是打乱的分数   分数最大为150
 * @date 2018/11/18 19:11
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] numbers = new int[10000];
        Random ran  = new Random(47);
        for (int i=0; i<10000; i++) {
            numbers[i] = ran.nextInt(150);
        }
        long startDate = System.currentTimeMillis();
        sort(numbers, numbers.length);
        System.out.println(System.currentTimeMillis() - startDate);
        System.out.println(Arrays.toString(numbers));
        System.out.println(count);
    }

    private static int count;

    private static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        //  获取初始数组里的最大值
        int max = a[0];
        for (int i=1; i<n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        //  用桶排序记录该分数下的学生人数，数组大小为最大值加1
        int[] b = new int[max+1];
        //  遍历原始数组，原始数组中的每个分数相当于分数记录数组的下标
        for (int i=0; i<n; i++) {
            b[a[i]]++;
        }

        //  把记录分数后的学生人数再次处理， 可以理解为该分数之前的人数统计，注意，第一个不用
        /*int min = b[0];
        int minIndex = 0;*/
        for (int i=1; i<b.length; i++) {
            /*if (min > b[i]) {
                min = b[i];
                minIndex = i;
            }*/
            b[i] += b[i-1];
        }
        /*System.out.println(min);
        System.out.println(minIndex);*/

        //  创建一个临时数组，把排序后的
        int[] temp = new int[n];
        //  是从尾部往前遍历
        for (int i=n-1; i>=0; i--) {
            temp[b[a[i]] - 1] = a[i];
            b[a[i]]--;
        }

        for (int i=0; i<n; i++) {
            a[i] = temp[i];
        }
    }
}
