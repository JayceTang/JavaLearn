package com.jayce.thinkinginjava;

import java.util.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestContainer_11_2
 * @description
 * @date 2018/10/8 21:52
 */
public class TestContainer_11_2 {
    public static void main(String[] args) {
        Set<Integer> c = new HashSet<>();
        for (int i=0; i<10; i++) {
            c.add(i);
        }
        Stack<String> strs = new Stack<>();
        for (Integer i : c) {
            System.out.println(i);
        }
    }
}
