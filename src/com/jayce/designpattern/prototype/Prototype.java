package com.jayce.designpattern.prototype;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/6 17:09
 * @description 原型模式
 * 原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。
 * 这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 *
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * 所谓原型模式，就是java中的克隆技术，以某个对象为原型。复制出新的对象。显然新的对象具备原型对象的特点。
 * 1、实现克隆操作，在 JAVA 继承 Cloneable，重写 clone()。
 * 2、原型模式同样用于隔离类对象的使用者和具体类型（易变类）之间的耦合关系，它同样要求这些"易变类"拥有稳定的接口。
 */
public class Prototype {
    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.setMsg("生日快乐!");

        Mail mail2 = (Mail) mail.clone();
        System.out.println("小明:"+mail.getMsg());
        System.out.println("小红:"+mail2.getMsg());
    }
}

/**
 * 定义一个原型的邮件信息
 */
class Mail implements Cloneable {
    protected String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
    void sendMail() {
    }
}

class BirthdayMail extends Mail {

    public BirthdayMail() {
        msg = "生日快乐!";
    }

    @Override
    void sendMail() {
        System.out.println(msg);
    }
}
