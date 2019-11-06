package com.jayce.designpattern.singleton;

public class Singleton {

}

/**
 * 饿汉式
 */
class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

}

/**
 * 饱汉式
 */
class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

/**
 * 方法三
 *   静态内部类
 * 这种写法仍然使用JVM本身机制保证了线程安全问题；由于SingletonTest5是私有的， 除了getInstance()之外没有办法访问它，
 * 因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖JDK版本。
 */
class  Singleton3 {
    private Singleton3(){
    }
    private static class Singleton4{
        private static Singleton3 instance = new Singleton3();
    }
    public static final Singleton3 getInstance(){
        return Singleton4.instance;
    }
}


/**
 * 双重锁检查
 */
class Singleton5 {
    private static volatile Singleton5 instance;

    private Singleton5() {}

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}

/**
 * 方法六，枚举单例模式
 * 1.从Java1.5开始支持;
 * 2.无偿提供序列化机制;
 * 3.绝对防止多次实例化，即使在面对复杂的序列化或者反射攻击的时候;
 * 自由序列化，线程安全，保证单例
 */
enum  Singleton6 {
    /**
     * 测试继承
     */
    INSTANCE {
        @Override
        protected void read() {
            System.out.println("reading");
        }

        @Override
        protected void write() {
            System.out.println("writing");
        }
    };

    protected abstract void read();
    protected abstract void write();
}