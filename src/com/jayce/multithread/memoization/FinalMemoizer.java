package com.jayce.multithread.memoization;

import com.jayce.util.ExceptionUtil;

import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName FinalMemoizer
 * @description 利用并发容器的 “没有则添加”方法，对返回值再做一个判断，就能解决上一个版本的问题。
 *      对于在计算中取消的exception，也做了从缓存中删除的异常处理！
 * @date 2019/4/30 17:18
 */
public class FinalMemoizer<A, V> implements Computable<A, V> {

    private final ConcurrentMap<A, FutureTask<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public FinalMemoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            FutureTask<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                //  如果不存在则添加
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw ExceptionUtil.launderThrowable(e.getCause());
            }
        }
    }
}
