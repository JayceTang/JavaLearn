package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName SelectionSort
 * @description 选择排序
 * @date 2018/11/17 8:22
 */
public class SelectionSort {
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
        for (int i=0; i<n-1; i++) {
            int minIndex = i;
            for (int j=i+1; j<n; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }
}

