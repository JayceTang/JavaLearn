package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestOptionalTrailingArgument
 * @description
 * @date 2018/9/24 15:14
 */
public class TestOptionalTrailingArgument {
    public static void f1(String s, Object...args) {
        System.out.println(s);
        for (Object o : args) {
            System.out.print(o.toString() + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f1("nice", "good","lucky","beautiful", "angel");
    }
}
