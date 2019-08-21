package com.jayce.thinkinginjava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName GeneritWriting_15_10_2
 * @description 逆变
 * @date 2018/11/5 23:10
 */
public class GeneritWriting_15_10_2 {
    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static List<Fruit> fruits = new ArrayList<>();
    static List<Apple> apples = new ArrayList<>();

    static void f1() {
        writeExact(apples, new Apple(1));
        //  书里说这里不能放进去,应该是改进过了泛型的工作机制
        writeExact(fruits, new Apple(2));
    }

    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {
        writeWithWildcard(apples, new Apple(3));
        writeWithWildcard(fruits, new Apple(4));
    }

    public static void main(String[] args) {
        f1();
        System.out.println(apples.get(0).toString());
        System.out.println(fruits.get(0).toString());
        f2();
        System.out.println(apples.get(1).toString());
        System.out.println(fruits.get(1).toString());
    }
}
class Fruit {
}

class Apple extends Fruit {

    private int id;

    public Apple() {
    }

    public Apple(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "apple id = " + id;
    }
}
