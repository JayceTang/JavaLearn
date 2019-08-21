package com.jayce.multithread;

import com.jayce.test.TestClass;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName OneValueCache
 * @description 使用final修饰变成不可变对象后，那么在初次初始化后，
 *                  该对象即不可改变，那么使用正确可保证是线程安全的
 * @date 2019/4/26 17:48
 */
@ThreadSafe
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;
    //  此方法可保证这个对象是安全的，或者说可以确保泛型的可见性是正确的！
    private AtomicReference<TestClass> ar;
    //  这里同上
    private volatile TestThread testThread;
    //  使用静态初始化函数也能发布一个线程安全的对象
    private static BigDecimal bd = new BigDecimal(0);

    /**  想创建这样的final类型成员变量来使用，
     *  那么必须在构造方法中初始化，
     *  并且该final类型的变量声明时必须不指向任何变量，连null变量也不能指向
     */
    public OneValueCache(BigInteger i, BigInteger[] factors, TestClass testClass, TestThread tt) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
        ar = new AtomicReference<>(testClass);
        testThread = tt;
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
