package com.jayce.test;

import java.util.Arrays;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Test
 * @description
 */
class Test {
    private static int counter = 0;
    private final int a = counter++;
    public static void main(String[] args) {
        boolean[] b = new boolean[10];
        System.out.println(Arrays.toString(b));
        Test t1 = new Test();
        Test t2 = new Test();
        System.out.println(t1.a);
        System.out.println(t1.a);
        System.out.println(t2.a);
        System.out.println(t1.a);
        System.out.println(t2.a);
        String a = "123";
        String c = "123";
        System.out.println(a == c);

    }

    public Test() {
        System.out.println(this);
        System.out.println(super.toString());
    }

    @Override
    public String toString() {
        return "haha";
    }

    public Test(int i) {
        System.out.println(this);
        System.out.println(super.toString());
    }
}

public class SubTest extends Test {
    public SubTest() {
    }

    public static void main(String[] args) {
        new SubTest();
        new Test();
        new Test(1);
    }

    @Override
    public String toString() {
        return "hehe";
    }
}
