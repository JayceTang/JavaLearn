package com.jayce.test;

import java.util.ArrayList;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName AbstractTestCalss
 * @description
 * @date 2018/12/9 10:33
 */
public abstract class AbstractTestClass implements TestInterface {

    protected ArrayList<Integer> list = new ArrayList<>();

    @Override
    public void add(int a) {
        list.add(a);
    }

    @Override
    public abstract int get(int index);

    abstract void haha();
}
