package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestAnonymousInnerClass_10_6
 * @description
 * @date 2018/10/6 18:19
 */
public class TestAnonymousInnerClass_10_6 {
    public static Contents getContents() {
        return new Contents(){

            @Override
            public int getInt(int i) {
                return i;
            }
        };
    }
    public static void main(String[] args) {
        Contents contents = getContents();
        System.out.println(contents.getInt(10));
    }
}

interface Contents {
    int getInt(int i);
}
