package com.jayce.multithread.memoization;

import java.math.BigInteger;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName ExpensiveFunction
 * @description
 * @date 2019/4/30 16:05
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //  假设经过漫长的计算过程  。。。
        return new BigInteger(arg);
    }
}
