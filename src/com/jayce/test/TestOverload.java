package com.jayce.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestOverload
 * @description
 * @date 2018/12/11 6:39
 */
public class TestOverload {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i=-3; i<3; ++i) {
            set.add(i);
            list.add(i);
        }
        for (int i=0; i<3; i++) {
            set.remove(i);
            //  plus Integer type
            list.remove(i);
        }
        System.out.println(set);
        System.out.println(list);
    }
}
