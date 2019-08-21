package com.jayce.thinkinginjava;

/**
 * @ClassName ShowSystemProperties
 * @Descripdtion
 * @author jayce tang
 * @ate 2018/9/16 22:01
 * @version 1.0
 */
public class ShowSystemProperties {
    public static void main(String[] args) {
        /*System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));*/

        /*SingletonClass sc1 = SingletonClass.newInstance();
        SingletonClass sc2 = SingletonClass.newInstance();
        System.out.println(sc1 == sc2);
        print("666"); */

        Dog d1 = new Dog(1,2);
        Dog d2 = new Dog(1,2);
        System.out.println(d1.equals(d2));
    }
}
