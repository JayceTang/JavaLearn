package com.jayce.algorithmslearn;

import java.util.Arrays;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName LinkedStack  链式栈   先进后出
 * @description   看了java编程思想里的 ,觉得他的实现更简单,只是有点不容易理解,不过很帅!
 * @date 2018/10/29 22:16
 */
public class LinkedStack<T> {
    private class Node<U> {
        private U item;
        private Node<U> next;

        public Node() {
            item = null;
            next = null;
        }
        public Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }
        public boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();

    public boolean push(T t) {
        top = new Node(t, top);
        return true;
    }
    public T pop() {
        T temp = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return temp;
    }
    public static void main(String[] args) {
        LinkedStack<String> u2 = new LinkedStack<>();
        for (String s : Arrays.asList("Welcome to china".split(" "))) {
            u2.push(s);
        }
        String s;
        while ((s = u2.pop()) != null) {
            System.out.println(s);
        }
    }
}
