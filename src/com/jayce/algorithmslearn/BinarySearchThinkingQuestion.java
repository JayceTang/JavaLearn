package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @Date: 2018/11/20 15:18
 * @Description:    如何实现一个值等于给定值的二分查找算法，数组为有序循环数组
 *
 * 实现思路  发现一个规律，如果mid把数组分开之后，那么一边是有序数组，一边则仍是有序循环数组，
 *    如果首元素（始终为二分的整个有序循环数组的首元素） 大于元素mid，那么mid右边的则是有序的数组，左边则可以理解为有序且循环的数组
 *    如果首元素（始终为二分的整个有序循环数组的首元素） 小于元素mid，那么mid左边的则是有序的数组，右边则可以理解为有序且循环的数组
 *    二分查找有序的数组里是否存在给定值，如果无，则二分有序循环数组获取新的mid，重复以上步骤，如找不到等于给定值的值则返回-1
 *
 *    该题在LeetCode上第33题
 */

public class BinarySearchThinkingQuestion {
    public static void main(String[] args) {
        //  有序循环数组
        int[] a = {4,5,6,7,0,1,2};
        int index = sort(a, 0,a.length-1, 0);
        if (index != -1) {
            System.out.println(index);
        } else {
            System.out.println("未找到该值");
        }
    }

    private static int sort(int[] a, int low, int high, int value) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[low] < a[mid]) {
                //  则mid左边是有序的
                int index = bsearch(a, low, mid - 1, value);
                if (index != -1) {
                    return index;
                }
                return sort(a, mid + 1, high, value);
            } else {
                //  则mid右边是有序的
                int index = bsearch(a, mid+1, high, value);
                if (index != -1) {
                    return index;
                }
                return sort(a, low, mid-1, value);
            }
        }

        return -1;
    }

    private static int bsearch(int[] a, int low, int high, int value) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
