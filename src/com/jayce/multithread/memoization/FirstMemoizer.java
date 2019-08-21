package com.jayce.multithread.memoization;

import java.util.HashMap;
import java.util.Map;
/**
 * @author jayce tang
 * @version 1.0
 * @ClassName FirstMemoizer
 * @description 第一次尝试做缓存器，使用HashMap，但很明显，线程安全性是有了，但很明显性能效率很慢
 * @date 2019/4/30 16:11
 */
public class FirstMemoizer<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public FirstMemoizer(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
