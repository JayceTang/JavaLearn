package com.jayce.test;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Test
 * @description
 * @date 2019/4/9 23:12
 */
public class Example {

    public static void main(String[] args) {
        /*BlockingQueue<String> queue = new LinkedBlockingQueue<>(20);
        for (int i=0; i<21; i++) {
            try {
                queue.put("haha");
                System.out.println(i);
                System.out.println(Thread.interrupted());
            } catch (InterruptedException e) {
                System.out.println("asdfa");
                Thread.currentThread().interrupt();
            }
        }*/
        //   斐波那契数列
        System.out.println(test(5, 1, 1));
    }

    public static int test(int n, int a1, int a2) {
        if (n <= 2) {
            return a2;
        }
        return test(n-1, a2, a1+a2);
    }

}
