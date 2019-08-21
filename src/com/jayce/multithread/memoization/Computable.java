package com.jayce.multithread.memoization;

/**
 * @author jayce tang
 * @version 1.0
 * @InterfaceName Computable
 * @description
 * @date 2019/4/30 16:04
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
