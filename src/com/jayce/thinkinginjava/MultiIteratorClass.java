package com.jayce.thinkinginjava;

import java.util.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName MultiIteratorClass
 * @description
 * @date 2018/10/16 23:46
 */
public class MultiIteratorClass extends IteratorString_11_13 {
    public Iterable<String> reversed() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int count = words.length - 1;
                    @Override
                    public boolean hasNext() {
                        return count > -1;
                    }

                    @Override
                    public String next() {
                        return words[count--];
                    }
                };
            }
        };
    }

    public Iterable<String> randomized() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        MultiIteratorClass mic = new MultiIteratorClass();
        for (String s : mic.reversed()) {
            System.out.print(s + " ");
        }
        /**  这里要注意, 使用的随机数并没有改变数组内容的具体顺序,是因为实现时是把数组内容放入了一个容器当中
         *      来实现的, 而如果不放入容器来使用, 则会直接改变底层的数组顺序
         */
        System.out.println();
        for (String s : mic.randomized()) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(mic.words));
    }
}
