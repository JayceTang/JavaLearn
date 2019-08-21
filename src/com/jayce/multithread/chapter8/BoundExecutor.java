package com.jayce.multithread.chapter8;

import java.util.concurrent.*;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/12 15:33
 * @description 使用Semaphore控制任务的提交速率
 */
public class BoundExecutor {
    private final Executor exec;
    private final Semaphore sema;

    public BoundExecutor(int bound) {
        exec = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        sema = new Semaphore(bound);
    }

    public void submitTask(final Runnable r) throws InterruptedException {
        sema.acquire();
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        r.run();
                    } finally {
                        sema.release();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            sema.release();
        }
    }
}
