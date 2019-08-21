package com.jayce.multithread.chapter8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 定制Thread基类
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/12 15:42
 * @description 为线程指定名字，设置自定义UncaughtExceptionHandler向logger中写入信息，维护一些统计信息（包括有多少个线程被创建和销毁），
 * 以及在线程被创建和终止时把调试信息写入日志
 */
public class MyAppThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    //  是否在debug,使用轻量级的volatile保证数据的可见性
    private static volatile boolean debugLifecycle = false;
    //  已创建的线程
    private static final AtomicInteger created = new AtomicInteger();
    //  存活的线程
    private static final AtomicInteger alive = new AtomicInteger();
    //  日志
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }
    public MyAppThread(Runnable r, String name) {
        super(r, name);
        setUncaughtExceptionHandler(
                /*new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
                    }
                }*/
                (t, e) -> log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e)
        );
    }

    @Override
    public void run() {
        //  复制debug标志以保证一致的值
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "Created " + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exiting " + getName());
            }
        }
    }

    public static int getThreadCreated() {
        return created.get();
    }
    public static int getThreadAlive() {
        return alive.get();
    }
    public static boolean getDebugLifecycle() {
        return debugLifecycle;
    }
    public static void setDebugLifecycle(boolean b) {
        debugLifecycle = b;
    }
}
