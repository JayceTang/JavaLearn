package com.jayce.designpattern.factory;

/**
 * 抽象工厂模式
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/6 16:02
 * @description
 * 抽象工厂模式是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 也就是提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 */
public class AbstractFactory {
    public static void main(String[] args) {
        FruitFactory factory1 = new NorthFactory();
        Fruit apple = factory1.getApple();
        apple.get();
        Fruit banana = factory1.getBanana();
        banana.get();
        System.out.println();

        FruitFactory factory2 = new SouthFactory();
        Fruit apple1 = factory2.getApple();
        apple1.get();
        Fruit banana1 = factory2.getBanana();
        banana1.get();
    }
}

/**
 * 抽象工厂模式是所有形态的工厂模式中最为抽象和最其一般性的。抽象工厂模式可以向客户端提供一个接口，
 * 使得客户端在不必指定产品的具体类型的情况下，能够创建多个产品族的产品对象。
 *
 * 1.抽象工厂（Creator）角色
 * 抽象工厂模式的核心，包含对多个产品结构的声明，任何工厂类都必须实现这个接口。(FruitFactory)
 *
 * 2.具体工厂（Concrete Creator）角色
 * 具体工厂类是抽象工厂的一个实现，负责实例化某个产品族中的产品对象。(NorthFruitFactory,SouthFruitFactory)
 *
 * 3.抽象（Product）角色
 * 抽象模式所创建的所有对象的父类，它负责描述所有实例所共有的公共接口。(Fruit)
 *
 * 4.具体产品（Concrete Product）角色
 * 抽象模式所创建的具体实例对象(NorthApple,NorthBanana,SouthApple,SouthBanana)
 * 总结：抽象工厂中方法对应产品结构，具体工厂对应产品族。
 */

interface Fruit {
    void get();
}

abstract class Apple implements Fruit {
    @Override
    public abstract void get();
}

abstract class Banana implements Fruit {
    @Override
    public abstract void get();
}

interface FruitFactory {
    /**
     * 实例化Apple
     */
    Fruit getApple();
    /**
     * 实例化Banana
     */
    Fruit getBanana();
}

class SouthApple extends Apple {
    @Override
    public void get() {
        System.out.println("长在南方的苹果");
    }
}

class SouthBanana extends Banana {
    @Override
    public void get() {
        System.out.println("长在南方的香蕉");
    }
}

class SouthFactory implements FruitFactory {

    @Override
    public Fruit getApple() {
        return new SouthApple();
    }

    @Override
    public Fruit getBanana() {
        return new SouthBanana();
    }
}

class NorthApple extends Apple {

    @Override
    public void get() {
        System.out.println("长在北方的苹果");
    }
}

class NorthBanana extends Banana {
    @Override
    public void get() {
        System.out.println("长在北方的香蕉");
    }
}

class NorthFactory implements FruitFactory {

    @Override
    public Fruit getApple() {
        return new NorthApple();
    }

    @Override
    public Fruit getBanana() {
        return new NorthBanana();
    }
}