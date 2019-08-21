package com.jayce.multithread.addreliablecancel;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/7/28 11:07
 * @description 为日志服务中的线程添加可靠的取消和关闭
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final PrintWriter writer;
    private final LoggerThread loggerThread;
    private static final int DEFAULT_CAPACITY = 20;
    private boolean isShutdown;
    private int reservations;

    public LogService(Writer writer) {
        this(writer, DEFAULT_CAPACITY);
    }

    public LogService(Writer writer, int capacity) {
        queue = new LinkedBlockingQueue<>(capacity);
        this.writer = new PrintWriter(writer);
        loggerThread = new LoggerThread();
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("state error");
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        /* retry */
                    }
                }
            } finally {
                writer.close();
            }
        }
    }
}
