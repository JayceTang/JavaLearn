package com.jayce.multithread.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestHarness
 * @description     //  测试二元闭锁
 * @date 2019/4/29 17:22
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i=0; i<nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        try {
            long time = new TestHarness().timeTasks(5,() -> {
                System.out.println("so good!");
            });
            System.out.println(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
