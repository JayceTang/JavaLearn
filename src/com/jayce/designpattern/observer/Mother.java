package com.jayce.designpattern.observer;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/7/5 14:34
 * @description
 */
public class Mother implements Monitor {

    @Override
    public void handle(Event e) {
        System.out.println("mom：哭啥哭");
    }
}
