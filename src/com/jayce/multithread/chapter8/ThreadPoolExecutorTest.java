package com.jayce.multithread.chapter8;


import java.util.concurrent.*;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/9 16:08
 * @description 创建自定义的线程池
 */
public class ThreadPoolExecutorTest {
    public final ThreadPoolExecutor exec;
    public ThreadPoolExecutorTest() {
        exec = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
    }
}
