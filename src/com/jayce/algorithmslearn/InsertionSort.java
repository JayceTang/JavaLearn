package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName InsertionSort
 * @description 插入排序
 * @date 2018/11/17 8:12
 */
public class InsertionSort {
    public static void main(String[] args) {
        Random ran = new Random(47);
        int[] a = new int[10000];
        for (int i=0; i<10000; i++) {
            a[i] = ran.nextInt(10000);
        }
        long startTime = System.currentTimeMillis();
        sort(a, a.length);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i=1; i<n; i++) {
            int value = a[i];
            int j = i-1;
            for (; j>=0; j--) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    //  如果这里没有退出，那么j的值会一直减下去
                    break;
                }
            }
            a[j+1] = value;
        }
    }
}
