package com.jayce.thinkinginjava;

import java.util.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestHashSetAndTreeSet_11_9
 * @description
 * @date 2018/10/10 22:54
 */
public class TestHashSetAndTreeSet_11_9 {
    public static void hashSet() {
        Random ran = new Random(47);
        Set<Integer> set = new HashSet<Integer>();
        for (int i=0; i<10000; i++) {
            set.add(ran.nextInt(30));
        }
        System.out.println("HashSet : " + set);
    }

    public static void iterator() {
        Random ran = new Random(47);
        SortedSet<Integer> set = new TreeSet<>();
        for (int i=0; i<10000; i++) {
            set.add(ran.nextInt(30));
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println(integer);
        }
    }

    public static void treeSet() {
        Random ran = new Random(47);
        SortedSet<Integer> set = new TreeSet<>();
        for (int i=0; i<10000; i++) {
            set.add(ran.nextInt(30));
        }
        System.out.println("TreeSet : " + set);
    }

    public static void main(String[] args) {
        hashSet();
        treeSet();
//        iterator();
    }
}
