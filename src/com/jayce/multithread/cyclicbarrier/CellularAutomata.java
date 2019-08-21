package com.jayce.multithread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName CellularAutomata
 * @description     没有实验完成
 * @date 2019/4/30 13:56
 */
public class CellularAutomata {
    int count = Runtime.getRuntime().availableProcessors();
    CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
        }
    });
}
