package com.jayce.multithread.memoization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName SecondMomoizer
 * @description 使用concurrentMap来代替Map来改进Memoizer中的并发行为,多线程可以并发的使用它。
 *       但它作为缓存还是有一个漏洞--当两个线程同时调用compute方法时，可能会出现同样的计算结果。
 *       意思是，第一个线程计算过程比较长时，第二个线程带着同样的参数进入方法，又再次重新计算一次，带来很明显
 *       的低效作用，而缓存的作用本身就是避免数据被计算多次
 * @date 2019/4/30 16:26
 */
public class SecondMomoizer<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public SecondMomoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
