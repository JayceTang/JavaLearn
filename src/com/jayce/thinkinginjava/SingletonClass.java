package com.jayce.thinkinginjava;

/**
 * @ClassName SingletonClass
 * @Description produce one singleton object
 * @author jayce tang
 * @Date 2018/9/16 23:22
 * @version 1.0
 */
public class SingletonClass {
    private static SingletonClass sc = new SingletonClass();

    public synchronized static SingletonClass newInstance() {
        return sc;
    }

    private SingletonClass(){};
}
