package com.jayce.designpattern.adapter;

/**
 * @author Jayce Tang

 * @version 1.0
 * @date 2019/11/7 9:28
 * @description 适配器模式
 * 将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 有两种模式
 * 1.类适配器模式
 * 2.对象适配器模式
 *
 * ps:
 * 适配器模式是作为两个不兼容的接口之间的桥梁。这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
 * 适配器不是在详细设计时添加的，而是解决正在服役的项目的问题。
 */
public class Adapter {
    public static void main(String[] args) {
        /*
         * 1.类适配器模式
         * 通过继承来实现适配器功能。
         * 有一个视频播放器，但是只能播放MP4格式的视频
         * 有一个AVI格式的视频需要播放，这时便可以使用格式工厂软件进行转换(适配器)进行转换成MP4格式，然后就可以播放了
         */
        Mp4 mp4=new VideoPlayer();
        mp4.playMp4();
        Avi avi=new FormatFactory();
        avi.playAvi();

        /*
         * 2.对象适配器模式
         * 通过组合来实现适配器功能。
         * 推荐使用对象适配器模式，设计原则的合成复用原则中描述，尽量使用合成/聚合的方式，而不是使用继承。
         */
        Rvmb rvmb=new FormatFactory2(new VideoPlayer());
        rvmb.playRvmb();

        Cat cat = new FlowerCat(new YellowDog());
        cat.skip();
    }
}

interface Mp4{
    void playMp4();
}

interface Avi{
    void playAvi();
}

interface Rvmb{
    void playRvmb();
}

/**
 * 视频播放器
 */
class VideoPlayer implements Mp4{

    @Override
    public void playMp4() {
        System.out.println("播放Mp4格式的视频文件.");
    }
}

/**
 * 格式工厂
 */
class FormatFactory extends VideoPlayer  implements Avi{

    @Override
    public void playAvi() {
        //转换成MP4格式的视频
        playMp4();
    }
}

/**
 * 格式工厂
 */
class FormatFactory2  implements Rvmb{
    private Mp4 mp4;

    public FormatFactory2(Mp4 mp4) {
        this.mp4=mp4;
    }

    @Override
    public void playRvmb() {
        mp4.playMp4();
    }

}

interface Dog {
    void run();
}

interface Cat {
    void skip();
}

class YellowDog implements Dog {

    @Override
    public void run() {
        System.out.println("running...");
    }
}

class FlowerCat implements Cat {

    private Dog dog;

    public FlowerCat(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void skip() {
        dog.run();
    }
}
