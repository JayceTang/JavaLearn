package com.jayce.multithread.executorframework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName SingleThreadWebServer
 * @description 串行的执行用户对web服务器的请求  ,很明显，若处理的时间很久，那响应度和吞吐量都会很糟糕！
 * @date 2019/5/1 0:41
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            Handler.handleRequest(connection);
        }
    }
}
