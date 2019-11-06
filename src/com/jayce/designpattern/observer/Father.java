package com.jayce.designpattern.observer;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/7/5 14:38
 * @description
 */
public class Father implements Monitor {

    @Override
    public void handle(Event e) {
        System.out.println("father:再哭就打了");
    }
}
