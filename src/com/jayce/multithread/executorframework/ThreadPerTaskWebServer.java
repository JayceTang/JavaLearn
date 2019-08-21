package com.jayce.multithread.executorframework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName ThreadPerTaskWebServer
 * @description 对每一个请求任务都分配一个线程，但是根本没有任何对线程数量的限制，那么在造成3种严重的后果
 *          1.线程生命周期的消耗非常高
 *          2.资源消耗
 *          3.稳定性           p95
 * @date 2019/5/1 0:47
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> Handler.handleRequest(connection);
            new Thread(task).start();
        }
    }
}
