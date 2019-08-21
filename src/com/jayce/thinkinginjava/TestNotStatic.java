package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestNotStatic
 * @description
 * @date 2018/9/17 22:58
 */
public class TestNotStatic {
    public static int test(int a) {
        return a > 5 ? a : 5;
    }

    public static void main(String[] args) {
        System.out.println(test(1));
    }
}
