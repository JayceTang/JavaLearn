package com.jayce.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName PriorityThread
 * @description
 * @date 2019/4/23 20:36
 */
public class PriorityThread {
    private PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();

    private final AtomicLong atomicLong = new AtomicLong(0);

    private String name;

    public PriorityThread(String name) {
        this.name = name;
    }
    public void atomicIncrement() {
        // 原子性的自增，线程安全的
        atomicLong.incrementAndGet();
    }

    public synchronized void test1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name);
    }

    public synchronized void test2() {
        System.out.println("six six six!");
    }

    public long get() {
        return atomicLong.get();
    }

    public static void main(String[] args) {
        PriorityThread p1 = new PriorityThread("one");
        PriorityThread p2 = new PriorityThread("two");
        new TestThread(p1, 1).start();
        new TestThread(p1, 0).start();



    }
}

class TestThread extends Thread {

    public PriorityThread p;
    public int index = 0;

    public TestThread(PriorityThread p, int index) {
        this.p = p;
        this.index = index;
    }
    @Override
    public void run() {
        if (index == 1) {
            p.test1();
        } else {
            p.test2();
        }
    }
}

class TestCall implements Callable<String> {

    @Override
    public String call() throws Exception {
        return null;
    }
}