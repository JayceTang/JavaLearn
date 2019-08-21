package com.jayce.designpattern.Observer;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/7/5 14:34
 * @description
 */
public class GrandFather implements Monitor {

    @Override
    public void handle(Event e) {
        System.out.println("grandfather: 呵呵");
    }
}
