package com.jayce.multithread.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName HoeseRace
 * @description 马赛仿真
 * @date 2018/11/30 23:06
 */
public class HorseRace {
    private static final int FINISH_LINE = 100;
    private List<Horse> horses = new ArrayList<>();
    private CyclicBarrier barrier;
    private ExecutorService exec = Executors.newCachedThreadPool();
    public HorseRace(int nHorses, long pause) {
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            //  这里应该是先执行完 全部的多线程,让他们全部挂起,然后执行一次这里,然后在全部发信号抢跑 - -
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                for (int i=0; i<FINISH_LINE; i++) {
                    builder.append("=");
                }
                System.out.println(builder);
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + " won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("HorseRace-action sleep interrupted!");
                }
            }
        });
        for (int i=0; i<nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        new HorseRace(7, 200);
    }
}

class Horse implements Runnable {
    private static int counter = 1;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    public synchronized int getStrides() {
        return strides;
    }
    private static CyclicBarrier barrier;
    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized(this) {
                    strides += random.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            //
        } catch (BrokenBarrierException e) {
            //
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id;
    }

    public String tracks() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<getStrides(); i++) {
            builder.append("*");
        }
        builder.append(id);
        return builder.toString();
    }
}



