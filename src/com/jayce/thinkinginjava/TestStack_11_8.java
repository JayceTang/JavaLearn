package com.jayce.thinkinginjava;

import java.util.LinkedList;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestStack_11_8
 * @description
 * @date 2018/10/9 23:07
 */
public class TestStack_11_8 {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("name");
        String name1 = stringStack.peek();
        System.out.println(name1);
        System.out.println(stringStack.isEmpty());
        String name2 = stringStack.pop();
        System.out.println(name2);
        System.out.println(stringStack.isEmpty());
    }
}

class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T t) {
        list.add(t);
    }
    public T peek() {
        return list.getFirst();
    }
    public T pop() {
        return list.removeFirst();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
