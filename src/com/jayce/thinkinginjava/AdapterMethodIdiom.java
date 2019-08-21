package com.jayce.thinkinginjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName AdapterMethodIdiom
 * @description
 * @date 2018/10/16 22:43
 */
class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int count = size() - 1;
                    @Override
                    public boolean hasNext() {
                        return count > -1;
                    }

                    @Override
                    public T next() {
                        return get(count--);
                    }
                };
            }
        };
    }
}

public class AdapterMethodIdiom {
    public static void main(String[] args) {
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(Arrays.asList("To be or not to be".split(" ")));
        for (String s : ral) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : ral.reversed()) {
            System.out.print(s + " ");
        }
    }
}
