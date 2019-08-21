package com.jayce.thinkinginjava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestHashCode
 * @description
 * @date 2018/11/19 22:59
 */
public class TestHashCode {
    public static void main(String[] args) {
        String s = "haha";
        System.out.println(s.hashCode());
        String s1 = "haha";
        System.out.println(s1.hashCode());
        List<String> list = new ArrayList<>();
        list.clear();
    }
}
