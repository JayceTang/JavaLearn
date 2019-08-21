package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName BubbleSort
 * @description 冒泡排序
 * @date 2018/11/17 8:07
 */
public class BubbleSort {
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
        for (int i=0; i<n; i++) {
            boolean flag = false;
            for (int j=0; j<n-i-1; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

    }
}
