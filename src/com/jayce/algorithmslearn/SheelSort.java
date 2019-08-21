package com.jayce.algorithmslearn;

/**
 * 希尔排序
 */
public class SheelSort {
    public static void main(String[] args) {

        int[] nums = {3, 53, 34, 4, 34, 2, 43, 345, 353, 244};
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+ " " );
        }
        System.out.println();
        System.out.println(System.currentTimeMillis());
        new SheelSort().compare(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " " );
        }
        System.out.println();
        System.out.println(System.currentTimeMillis());
    }

    public void compare(int[] a) {
        int d = a.length;
        while (d != 0) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                //组中的元素，从第二个数开始
                for (int i = x + d; i < a.length; i += d) {
                    //j为有序序列最后一位的位数
                    int j = i - d;
                    //要插入的元素
                    int temp = a[i];
                    //从后往前遍历。
                    for (; j >= 0 && temp < a[j]; j -= d) {
                        //向后移动d位
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
        }
    }
}
