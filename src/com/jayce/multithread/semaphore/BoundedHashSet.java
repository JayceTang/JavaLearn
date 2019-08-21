package com.jayce.multithread.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName BoundedHashSet
 * @description     一个有界并且可堵塞获取而不是立即失败的set
 * @date 2019/4/30 12:24
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            //  看hashSet源码，add会对比返回的oldValue是否为空，为空则为新增，返回true,所以不释放,
            //  不释放则可理解为  semaphore绑定了该值，它的state-1
            //  不为空则为替换原来的值，返回false，容器可视为未添加任何元素，立即释放
            //  释放则绑定取消，仍然可以继续增加
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<Integer> bh = new BoundedHashSet<>(10);
        bh.add(12);
        bh.add(23);
        bh.add(12);
        System.out.println(bh);
    }
}
