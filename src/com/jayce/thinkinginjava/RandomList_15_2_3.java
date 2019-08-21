package com.jayce.thinkinginjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName RandomList
 * @description
 * @date 2018/10/29 22:37
 */
public class RandomList_15_2_3<T> {
    private List<T> list = new ArrayList<>();
    private Random ran = new Random(47);
    public void add(T t) {
        list.add(t);
    }
    public T get() {
        return list.get(ran.nextInt(list.size()));
    }
    public static void main(String[] args) {
        RandomList_15_2_3<String> ran = new RandomList_15_2_3<>();
        for (String s : Arrays.asList("I will get better and better".split(" "))) {
            ran.add(s);
        }
        for (int i=0; i<6; i++) {
            System.out.println(ran.get());
        }
    }
}
