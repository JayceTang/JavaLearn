package com.jayce.algorithmslearn;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: jayce tang
 * @date: 2018/12/3 14:02
 * @description:
 */
public class Heap {
    //  从下标1开始存储数据
    private int[] a;
    //  堆可以存储的最大数组个数
    private int n;
    //  堆中已经存储的数组个数
    private int count;

    public Heap(int capacity) {
        a = new int[capacity+1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        //  堆满了
        if (count >= n) {
            return;
        }
        a[++count] = data;
        int i = count;
        //  从下往上堆化
        while (i/2 > 0 && a[i] > a[i/2]) {
            swap(a, i, i/2);
            i = i/2;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count];
        count--;
        heapify(a, count, 1);
    }

    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i*2 <= n && a[i] < a[i*2]) {
                maxPos = i*2;
            }
            if (i*2+1 <= n && a[maxPos] < a[i*2+1]) {
                maxPos = i*2+1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    public void buildHeap(int[] a, int n) {
        for (int i=n/2; i>=1; i++) {
            heapify(a, n, i);
        }
    }

    public void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10000);
        Random ran  = new Random(47);
        for (int i=0; i<10000; i++) {
            heap.insert(ran.nextInt(10000));;
        }
        long startDate = System.currentTimeMillis();
        heap.sort(heap.a, heap.count);
        System.out.println(System.currentTimeMillis() - startDate);
        System.out.println(Arrays.toString(heap.a));
    }
}
