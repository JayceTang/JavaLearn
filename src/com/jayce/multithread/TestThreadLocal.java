package com.jayce.multithread;

import com.jayce.test.TestClass;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestThreadLocal
 * @description
 * @date 2019/4/25 22:07
 */
public class TestThreadLocal {
    private static ThreadLocal<String> threadHolder = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "哈哈";
        }
    };

    public String get() {
        return threadHolder.get();
    }

    private static TestClass testClass = new TestClass();
    private final TestClass t1 = new TestClass();

    public static void main(String[] args) {
        System.out.println(new TestThreadLocal().get());
        testClass = new TestClass();
        TestClass t2 = testClass;
        TestThreadLocal tt = new TestThreadLocal();
        TestClass t5 = testClass;
        testClass = new TestClass();
        //  可以指向该引用   tt.t1不能再指向其他的引用，这是最终状态
        TestClass t4 = tt.t1;
    }
}
