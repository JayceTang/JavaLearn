package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TextEnum
 * @description
 * @date 2018/9/24 15:57
 */
public class TestEnum {
    private Money MONEY;

    public TestEnum(Money MONEY) {
        this.MONEY = MONEY;
    }

    public static void printEnum() {
        for (Money m : Money.values()) {
            System.out.println(m.toString() + " , " + m.ordinal());
        }
    }

    public void switchEnum() {
        switch (MONEY) {
            case ONEMONEY:
                System.out.println("one monry!");
                break;
            case TWOMONEY:
                System.out.println("two money");
                break;
            case THREEMONEY:
                System.out.println("three money");
                break;
            case FOURMONEY:
                System.out.println("four money");
                break;
            case FIVEMONEY:
                System.out.println("five money");
                break;
        }
    }

    public static void main(String[] args) {
        printEnum();
        TestEnum
                t1 = new TestEnum(Money.FOURMONEY),
                t2 = new TestEnum(Money.THREEMONEY),
                t3 = new TestEnum(Money.ONEMONEY);
        t1.switchEnum();
        t2.switchEnum();
        t3.switchEnum();

    }
}
