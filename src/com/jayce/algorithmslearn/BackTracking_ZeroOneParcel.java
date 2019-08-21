package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @date: 2018/12/29 17:41
 * @description:
 */
public class BackTracking_ZeroOneParcel {
    // 存储背包中物品总重量的最大值
    public int maxW = Integer.MIN_VALUE;
    // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
    // w 背包重量；items 表示每个物品的重量；n 表示物品个数
    // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)

    public void f(int i, int cw, int[] items, int n, int w) {
        // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        f(i+1, cw, items, n, w);
        // 已经超过可以背包承受的重量的时候，就不要再装了
        if (cw + items[i] <= w) {
            f(i+1,cw + items[i], items, n, w);
        }
    }


    public static void main(String[] args) {
        /*int[] items = {1, 20, 13, 54, 53, 23, 12, 9, 34, 23, 21};
        Parcel_0_1 parcel_0_1 = new Parcel_0_1();
        parcel_0_1.f(0, 0, items, 10, 100);
        System.out.println(parcel_0_1.maxW);*/

    }
}
