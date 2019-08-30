package com.jayce.multithread.chapter10;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/21 11:35
 * @description     在相互作用协作对象之间的锁由于顺序产生的死锁   及  修复方案
 */
public class CooperationOrderLockAndFixed {
    /**  车辆位置信息  */
    private static class Point {

    }
    /** 绘画实体    */
    private static class Image {

        public void drawMarker(Point location) {
        }
    }
    /***************************************************************************************
    *   10-6    在相互作用协作对象之间的锁由于顺序产生的死锁（错误示范）
     *  注意，容易发生死锁
     *  这里的问题在于，锁的粒度太大了，
     *      如果一个线程先执行Taxi1的getLocation方法，会获取taxi1的锁，执行notifyAvailable方法又会获取dispatcher的锁，
     *   但如果这时还有另一个线程在执行Dispatcher1的getImage方法，会获取dispatcher的锁，然后会执行getLocation方法，又会
     *   尝试去获取taxi1的锁，这就有可能因为顺序的问题而导致都在等待对方释放资源而造成死锁
    ****************************************************************************************/
    private static class Taxi1 {
        private Point location, destination;
        private final Dispatcher1 dispatcher1;

        public Taxi1(Dispatcher1 dispatcher1) {
            this.dispatcher1 = dispatcher1;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation(Point location) {
            this.location = location;
            //  这里的equals方法应重写，方法的意义相当于目前的位置是否为终点，为终点说明出租车可以另外分配地方去接客了
            if (location.equals(destination)) {
                dispatcher1.notifyAvailable(this);
            }
        }
    }

    private static class Dispatcher1 {
        private final Set<Taxi1> taxis;
        private final Set<Taxi1> availableTaxis;

        public Dispatcher1() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi1 taxi1) {
            availableTaxis.add(taxi1);
        }

        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi1 t : taxis) {
                image.drawMarker(t.getLocation());
            }
            return image;
        }
    }

    /***************************************************************************************/

    /***************************************************************************************
    *  10-7 ThreadSafe  通过公开调用来避免在相互协作的对象之间产生死锁
    *   降低锁的粒度，不使用同步方法，使用同步代码块，减少了锁的占有时间，还避免了死锁
     *   注意，有时编写同步代码块以使用开放调用时会产生意想不到的结果，因为这会使得某个原子操作变为非原子操作
    ****************************************************************************************/
    private static class Taxi2 {
        private Point location, destination;
        private final Dispatcher2 dispatcher2;

        public Taxi2() {
            dispatcher2 = new Dispatcher2();
        }

        public synchronized Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            boolean reachedDestination;
            //  粒度变小了
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }
            //  这里的equals方法应重写，方法的意义相当于目前的位置是否为终点，为终点说明出租车可以另外分配地方去接客了
            if (reachedDestination) {
                dispatcher2.notifyAvailable(this);
            }
        }
    }

    private static class Dispatcher2 {

        private final Set<Taxi2> taxis;
        private final Set<Taxi2> availableTaxis;

        public Dispatcher2() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi2 taxi2) {
        }

        public Image getImage() {
            Set<Taxi2> copy;
            //  粒度变小了
            synchronized (this) {
                copy = new HashSet<>(taxis);
            }
            Image image = new Image();
            for (Taxi2 t : copy) {
                image.drawMarker(t.getLocation());
            }
            return image;
        }
    }
}
