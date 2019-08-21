package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestExtends
 * @description   如果被继承的基类的构造器是有参数的,子类继承时必须实现基类的构造器
 * @date 2018/9/24 23:42
 */
public class TestExtends extends SuperClass {
    public TestExtends(int i) {
        super(i);
        System.out.println("TestExtends " + i);
    }

    public static void main(String[] args) {
        TestExtends testExtends = new TestExtends(1);
    }
}

class SuperClass {
    public SuperClass(int i) {
        System.out.println("SuperClass " + i);
    }
}
