package com.jayce.designpattern.bridge;

/**
 * 桥接模式
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/7 11:34
 * @description
 * 桥接是用于把抽象化与实现化解耦，使得二者可以独立变化。
 * 这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * 将抽象部分与实现部分分离，使它们都可以独立的变化。
 *
 * 使用场景
 * 一个类存在两个独立变化的维度，且这两个维度都需要进行扩展。
 */
public class Bridge {
    public static void main(String[] args) {
        Paper paper=new ExaminationPaper();
        paper.setPen(new RedPen());
        paper.writing();

        Paper paper2=new NewsPaper();
        paper2.setPen(new BlackPen());
        paper2.writing();
    }
}

/**
 * 笔
 */
interface Pen{
    void write();
}

class RedPen implements Pen{
    @Override
    public void write() {
        System.out.println("红色的字");
    }
}

class BlackPen implements Pen{
    @Override
    public void write() {
        System.out.println("黑色的字");
    }
}

abstract class  Paper{
    protected  Pen pen;

    void setPen(Pen pen){
        this.pen=pen;
    }

    abstract void writing();
}

/**
 * 考试用的卷子
 */
class ExaminationPaper extends Paper{
    @Override
    void writing() {
        pen.write();
    }
}

/**
 * 新闻报纸
 */
class NewsPaper extends Paper{
    @Override
    void writing() {
        pen.write();
    }
}
