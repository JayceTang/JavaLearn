package com.jayce.algorithmslearn;

import java.util.Arrays;

/**
 * @author: jayce tang
 * @Date: 2018/11/20 11:30
 * @Description:    （二分查找法变形1）查找第一个等于给定值的元素
 */
public class BinarySearch1 {
    public static void main(String[] args) {
        int[] a = {1, 3, 6, 4, 5, 5, 6, 7, 8, 2, 9 , 23 ,3};
        //  先使用insertionSort把他们排一下序
        insertionSort(a, a.length);
        System.out.println(Arrays.toString(a));
        //  查找第一个3的下标值
        int index = binarySort(a, a.length, 3);
        System.out.println(index);
        System.out.println(a[index]);
    }

    private static int binarySort(int[] a, int n, int value) {
        int high = n-1;
        int low = 0;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid -1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    private static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i=1; i<n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j>=0; j--) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = value;
        }
    }
}
