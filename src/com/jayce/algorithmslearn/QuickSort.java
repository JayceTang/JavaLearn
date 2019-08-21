package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName QuickSort
 * @description 快速排序
 * @date 2018/11/15 23:50
 */
public class QuickSort {
    public static void main(String[] args) {
        Random ran = new Random(47);
        int[] a = new int[10000];
        for (int i=0; i<10000; i++) {
            a[i] = ran.nextInt(10000);
        }
        long startTime = System.currentTimeMillis();
        quickSort(a, a.length);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(Arrays.toString(a));
    }

    private static void quickSort(int[] a, int n) {
        sort(a, 0, n-1);
    }

    private static void sort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(a, p, r);

        sort(a, p, q-1);
        sort(a, q+1, r);
    }

    public static int partition(int[] a, int p, int r) {
        int i = p;
        int piovt = a[r];

        int temp;
        for (int j=p; j<r; j++) {
            if (a[j] < piovt) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }

        temp = a[r];
        a[r] = a[i];
        a[i] = temp;

        return i;
    }
}
