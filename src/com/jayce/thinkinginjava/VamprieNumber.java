package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName VamprieNumber
 * @description 吸血鬼数字,  找出4位数内的所有吸血鬼数字
 * @date 2018/9/17 23:29
 */
public class VamprieNumber {
    public static void main(String[] args) {
        int x;
        int y;
        int z;
        int w;

        for (int i = 1000; i < 10000; i++) {

            x = i / 1000;
            y = (i - 1000 * x) / 100;
            z = (i - 1000 * x - 100 * y) / 10;
            w = i - 1000 * x - 100 * y - 10 * z;
            if (z == 0 && w == 0) {
                continue;
            }

            x *= 10;
            y *= 10;
            if ((x + z) * (y + w) == i) {
                System.out.println((x + z) + "*" + (y + w) + "=" + i);
                continue;
            }
            if ((x + w) * (y + z) == i) {
                System.out.println((x + w) + "*" + (y + z) + "=" + i);
                continue;
            }
            y /= 10;
            z *= 10;
            if ((x + y) * (z + w) == i) {
                System.out.println((x + y) + "*" + (z + w) + "=" + i);
                continue;
            }
            if ((x + w) * (y + z) == i) {
                System.out.println((x + w) + "*" + (y + z) + "=" + i);
                continue;
            }
            z /= 10;
            w *= 10;
            if ((x + y) * (z + w) == i) {
                System.out.println((x + y) + "*" + (z + w) + "=" + i);
                continue;
            }
            if ((x + z) * (y + w) == i) {
                System.out.println((x + z) + "*" + (x + z) + "=" + i);
                continue;
            }
            x /= 10;
            y *= 10;
            if ((x + y) * (z + w) == i) {
                System.out.println((x + y) + "*" + (z + w) + "=" + i);
                continue;
            }
            if ((z + y) * (x + w) == i) {
                System.out.println((z + y) + "*" + (x + w) + "=" + i);
                continue;
            }
            y /= 10;
            z *= 10;
            if ((z + y) * (x + w) == i) {
                System.out.println((z + y) + "*" + (x + w) + "=" + i);
                continue;
            }
            if ((z + x) * (y + w) == i) {
                System.out.println((z + x) + "*" + (y + w) + "=" + i);
                continue;
            }
            w /= 10;
            y *= 10;
            if ((x + y) * (z + w) == i) {
                System.out.println((x + y)+ "*" + (z + w) + "=" + i);
                continue;
            }
            if ((z + x) * (y + w) == i) {
                System.out.println((z + x) + "*" + (y + w) + "=" + i);
                continue;
            }


        }
    }
}
