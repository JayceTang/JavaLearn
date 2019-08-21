package com.jayce.thinkinginjava;

import java.util.Iterator;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Iterator_11_13
 * @description
 * @date 2018/10/16 22:00
 */
public class IteratorString_11_13 implements Iterable<String> {

    protected String[] words = "welcome to china my friend".split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    public static void main(String[] args) {
        IteratorString_11_13 iteratorString_11_13 = new IteratorString_11_13();
        Iterator iterator = iteratorString_11_13.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
