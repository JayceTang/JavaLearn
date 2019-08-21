package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestConstruction8_3_3
 * @description  主要目的是observe子类在构造器创建对象过程中会先初始化父类构造的构造器,
 *          那如果在此期间使用多态的方法调用会产生什么样的情况!
 * @date 2018/10/3 16:02
 */
public class TestConstruction8_3_3 {
    public static void main(String[] args) {
        RoundGlyph r = new RoundGlyph(20);
    }
}

class Glyph {
    public void draw() {
        System.out.println("Glyph draw ...");
    }

    public Glyph() {
        System.out.println("before glyph draw...");
        draw();
        System.out.println("after glyph draw...");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int radius) {
        this.radius = radius;
        System.out.println("RoundGlyph drawing ... radius = " + radius);
    }


    @Override
    public void draw() {
        System.out.println("RoundGlyph.draw(), radius = " + radius);
    }
}
