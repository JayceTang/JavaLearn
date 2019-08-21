package com.jayce.multithread.executorframework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TaskExecutionWebServer
 * @description 使用Executor框架，或者说是异步任务执行框架里的线程池来处理webServer的请求
 * @date 2019/5/1 0:57
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    /**
     看来阿里巴巴代码规范不建议直接使用Executors
     */
    /**
     * 这里可以通过更改Executor的实现行为，就可以更改服务器的行为
     * 如一下两个实现
     */
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    /**
        看来阿里巴巴代码规范的确是建议自己创建线程池。。。
     */
    private static final ExecutorService execu = new ThreadPoolExecutor(20,20,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true) {
            final Socket connection = serverSocket.accept();
            Runnable task = () -> Handler.handleRequest(connection);
            execu.execute(task);
        }
    }

    public static void stop() {
        execu.shutdown();
    }

}

/**
 * 为每一个请求创建一个新的线程
 */
class ThreadPerTaskExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}

/**
 * 同步的执行每一个任务，类似于单线程的行为，串行化
 */
class WithinThreadExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
