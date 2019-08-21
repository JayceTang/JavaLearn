package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestFinalize
 * @description
 * @date 2018/9/18 13:12
 */
public class TestFinalize {
    private boolean checkOut;
    private String name;

    public TestFinalize(boolean checkOut, String name) {
        this.checkOut = checkOut;
        this.name = name;
    }

    public void checkIn() {
        checkOut = false;
    }

    @Override
    protected void finalize() throws Throwable {
        if (checkOut) {
            System.out.println(name + " :Error : check out!");
        }
    }

    public static void main(String[] args) {
        TestFinalize test1 = new TestFinalize(true, "test1");
        test1.checkIn();
        new TestFinalize(true, "test2");
        new TestFinalize(true, "test3");
        System.gc();
    }
}
