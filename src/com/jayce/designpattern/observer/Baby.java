package com.jayce.designpattern.observer;

public class Baby extends AbstractEvent {

    @Override
    public void occur() {
        cry();
        notifyMonitor();
    }

    private void cry() {
        System.out.println("5555555555555...");
    }

    public static void main(String[] args) {
        Baby baby = new Baby();
        baby.addMonitor(new Mother());
        baby.addMonitor(new GrandFather());
        baby.occur();
        System.out.println();
        baby.addMonitor(new Father());
        baby.occur();
    }
}
