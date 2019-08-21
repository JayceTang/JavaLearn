package com.jayce.multithread.chapter9;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/15 11:46
 * @description 使用executor实现SwingUtilities
 *      可以将swing的事件线程是为executor的单线程，它处理来自事件队列的任务。
 */
public class SwingUtilities_9_1 {
    private static final ExecutorService exec = Executors.newSingleThreadExecutor(new SwingThreadFactory());
    private static volatile Thread swingThread;

    private static class SwingThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }

    /**  判断是否是事件线程 */
    public static boolean isEvenDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    /**  执行最近的线程  */
    public static void invokeLater(Runnable task) {
        exec.execute(task);
    }

    /**  提交线程计算并使用future来返回计算数据 */
    public static void invokeAndWait(Runnable task) throws InterruptedException, InvocationTargetException {
        Future<?> f = exec.submit(task);
        try {
            f.get();
        } catch (ExecutionException e) {
            throw new InvocationTargetException(e);
        }
    }
}
