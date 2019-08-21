package com.jayce.effectivejava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName SingletonImplment3
 * @description  singleton created for three way
 * @date 2018/10/2 21:13
 */
public enum SingletonImplment3 {
    INSTANCE;

    /** test use enum created singleton object*/
    public void speak() {
        System.out.println(this + "is speaking!");
    }

    public static void main(String[] args) {
        SingletonImplment3 s1 = SingletonImplment3.INSTANCE;
        s1.speak();
        SingletonImplment3 s2 = SingletonImplment3.INSTANCE;
        s2.speak();
        System.out.println(s1 == s2);
    }
}
