package com.jayce.multithread;

/**
 * @ClassName ProducerConsumer
 * @Description
 * @Author jayce tang
 * @Date 2018/7/27 22:15
 * @Version 1.0
 **/
public class ProducerConsumer {
    public static void main(String[] args) {
        Work work = new Work();
        new Producer(work).start();
        new Consumer(work).start();
    }
}
class Baozi {
    private int id;

    public Baozi(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Baozi{" +
                "id=" + id +
                '}';
    }
}

class Work {
    private int index = 0;
    private Baozi[] bs = new Baozi[6];

    public synchronized void produce(Baozi bz) {
        while (index == 6) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        bs[index] = bz;
        index++;
    }
    public synchronized Baozi consum() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        return bs[index];
    }
}

class Consumer extends Thread {
    private Work work;
    public Consumer(Work w) {
        this.work = w;
    }

    @Override
    public void run() {
        for (int i=0; i<20; i++) {
            Baozi baozi = work.consum();
            System.out.println("消费了 " + baozi);
        }
    }
}

class Producer extends Thread {
    private Work work;
    public Producer(Work w) {
        this.work = w;
    }
    @Override
    public void run() {
        for (int i=0; i<20; i++) {
            Baozi bz = new Baozi(i);
            work.produce(bz);
            System.out.println("生产了 " + bz);
        }
    }
}
