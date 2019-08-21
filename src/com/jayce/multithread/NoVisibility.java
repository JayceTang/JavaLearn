package com.jayce.multithread;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName NoVisibility
 * @description     数据不可见性改变了
 * @date 2018/12/16 19:01
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        ready = true;
        number = 47;
    }

}
