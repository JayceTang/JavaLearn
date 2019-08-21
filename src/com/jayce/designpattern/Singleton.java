package com.jayce.designpattern;

class Singleton {
    private static Singleton defaultClass = new Singleton();

    public static Singleton getInstince() {
        return defaultClass;
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstince();
        Singleton s2 = Singleton.getInstince();
        System.out.println(s1.equals(s2));

    }
}
