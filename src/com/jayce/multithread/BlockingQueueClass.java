package com.jayce.multithread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName BlockingQueueClass
 * @description
 * @date 2019/4/28 22:52
 */
public class BlockingQueueClass {
    public static void main(String[] args) {
        //  单向队列，以下两者适用于    生产者-消费者模式 （provider-consumer）
        Queue<User> queue = new ConcurrentLinkedQueue<>();
        /**
         *  BlockingQueue扩展了Queue，增加了可堵塞的插入和获取等操作：
         *  如果队列为空，获取元素的操作将一直堵塞，直到队列中出现一个可用的元素。
         *  如果队列已满，插入元素的操作将一直堵塞，直到队列出现可用的空间。
         *  所以用在高并发的环境下特别有用，比如说MQ，比如说秒杀活动的场景！
         */
        BlockingQueue<User> blockingQueue = new ArrayBlockingQueue<>(10);

        //  两者皆为双端队列,适用于   工作密取模式（Work stealing)
        //  扩展了Queue
        Deque<User> deque = new ArrayDeque<>();
        //  扩展了BlockingQueue
        BlockingDeque blockingDeque = new LinkedBlockingDeque();
    }
}
