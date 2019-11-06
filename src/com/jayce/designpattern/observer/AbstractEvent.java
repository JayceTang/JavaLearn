package com.jayce.designpattern.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/7/6 11:46
 * @description
 */
public class AbstractEvent implements Event {

    protected List<Monitor> monitors = new LinkedList<>();

    @Override
    public void occur() {

    }

    protected void addMonitor(Monitor m) {
        monitors.add(m);
    }

    //  只是测试，未考虑高并发
    protected void notifyMonitor() {
        for (Monitor monitor : monitors) {
            monitor.handle(this);
        }
    }
}
