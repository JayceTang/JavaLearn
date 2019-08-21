package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName MergeSort
 * @description  归并排序
 * @date 2018/11/13 21:30
 */
public class MergeSort {
    public static void main(String[] args) {
        Random ran = new Random(47);
        int[] a = new int[10000];
        for (int i=0; i<10000; i++) {
            a[i] = ran.nextInt(10000);
        }
        long startTime = System.currentTimeMillis();
        mergeSort(a, a.length);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(Arrays.toString(a));

    }

    public static void mergeSort(int[] a, int n) {
        sort(a, 0, n-1);
    }

    public static void sort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = p + ((r - p) >> 1);

        sort(a, p, q);
        sort(a, q+1, r);

        merge(a, p, q, r);
    }

    public static void merge(int[] a, int p, int q, int r) {
        int i=p, j=q+1, k=0;

        int[] temp = new int[r-p+1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        //  把原来数组剩下的数放到临时数组里
        int start = i, end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            temp[k++] = a[start++];
        }

        for (i=0; i<r-p+1; i++) {
            a[p+i] = temp[i];
        }
    }

}
