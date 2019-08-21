package com.jayce.test;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestClass
 * @description
 * @date 2018/12/9 10:36
 */
public class TestClass extends AbstractTestClass {
    @Override
    public int get(int index) {
        return list.get(index);
    }

    @Override
    void haha() {
        System.out.println("heihei");
    }

    public void da() {
        System.out.println("heihei");
    }

    public static void test() {
        System.out.println(111);
    }


    public static void main(String[] args) {
        int test = -342;
        System.out.println(test << 1);
        System.out.println(test >> 1);
        System.out.println(test >>> 1);

    }
}
