package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @Date: 2018/10/26 19:01
 * @Description:
 */
public class ArrayStack {
    private static final int DEFAULT_CAPACITY = 16;
    private String[] items;
    private int count;
    private int size;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    private ArrayStack(int size) {
        this.items = new String[size];
        this.size = size;
        this.count = 0;
    }

    public boolean push(String item) {
        if (count == size) {
            return false;
        }
        items[count++] = item;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String temp = items[count-1];
        count--;
        return temp;
    }
}
