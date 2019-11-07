package com.jayce.designpattern.factory;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/6 15:46
 * @description 简单工厂模式及工厂方法模式
 * 简单工厂模式是属于创建型模式，又叫做静态工厂方法模式。简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实例。
 * 调用只需要告诉工厂类所需要的类型，工厂类就会返回需要的产品类工厂的子类。 可以说是工厂模式中最简单的一种。
 */
public class FactoryTest {
    private static final String LOL="LOL";
    private static final String DNF="DNF";
    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 简单工厂模式
         * 根据条件决定一个接口由哪个具体产品类来实现
         * 优点:
         * 缺点:扩展性差
         */
        Game game= ComputerFactory.playGame(LOL);
        Game game2= ComputerFactory.playGame(DNF);
        game.play();
        game2.play();
        System.out.println();

        /**
         * 工厂方法模式
         *
         * 优点:扩展性高
         * 缺点:增加了复杂度
         */
        ComputerFactory2 cf = new LOLFactory();
        cf.playGame().play();
        cf = new DNFFactory();
        cf.playGame().play();
        cf = new WOWFactory();
        cf.playGame().play();
        System.out.println();
    }




}

/**
 * 定义一个接口
 */
interface Game{
    void play();
}

/**
 * 定义一个实现类
 */
class LOL implements Game{
    @Override
    public void play() {
        System.out.println("正在玩LOL...");
    }
}

class DNF implements Game{
    @Override
    public void play() {
        System.out.println("正在玩DNF...");
    }
}

class WOW  implements Game{
    @Override
    public void play() {
        System.out.println("正在玩WOW...");
    }
}

/**
 * 定义一个电脑
 */
class ComputerFactory{
    private static final String LOL="LOL";
    private static final String DNF="DNF";
    //玩游戏
    public static Game playGame(String game){
        if(LOL.equalsIgnoreCase(game)){
            return new LOL();
        }else if(DNF.equalsIgnoreCase(game)){
            return new DNF();
        }
        return null;
    }
}

interface ComputerFactory2{
    Game playGame();
}

class LOLFactory implements ComputerFactory2{
    @Override
    public Game playGame() {
        return new LOL();
    }
}

class DNFFactory implements ComputerFactory2{
    @Override
    public Game playGame() {
        return new DNF();
    }
}

class WOWFactory implements ComputerFactory2{
    @Override
    public Game playGame() {
        return new WOW();
    }
}
