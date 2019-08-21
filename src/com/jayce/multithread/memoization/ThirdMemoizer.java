package com.jayce.multithread.memoization;

import com.jayce.util.ExceptionUtil;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName ThirdMemoizer
 * @description 使用futureTask来解决上一个版本的问题，计算时如果出现另一个线程在计算同样的内容，那么会调用futureTask.get
 *      调用过程中如果还没计算完成则会发生堵塞，直到计算完成返回结果，并不会再重新计算一次
 * @date 2019/4/30 16:55
 */
public class ThirdMemoizer<A, V> implements Computable<A, V> {

    private final Map<A, FutureTask<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public ThirdMemoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        FutureTask<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw ExceptionUtil.launderThrowable(e.getCause());
        }
    }
}
