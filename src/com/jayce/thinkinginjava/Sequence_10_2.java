package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Sequence_10_2
 * @description
 * @date 2018/10/5 17:59
 */
interface Seletor {
    boolean end();
    Object current();
    void next();
}
public class Sequence_10_2 {
    private Object[] items;
    private int size = 0;
    public Sequence_10_2(int size) {
        items = new Object[size];
    }
    public void add(Object o) {
        if (size < items.length) {
            items[size++] = o;
        }
    }

    private class SequenceSeletor implements Seletor {
        private int i = 0;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) {
                i++;
            }
        }

        /**
         * return outer class reference
         */
        public Sequence_10_2 getOuterClass() {
            return Sequence_10_2.this;
        }
    }

    public Seletor getSeletor() {
        return new SequenceSeletor();
    }

    public static void main(String[] args) {
        Sequence_10_2 sequence = new Sequence_10_2(10);
        for (int i = 0; i < sequence.items.length; i++) {
            sequence.add(Integer.valueOf(i));
        }
        Seletor seletor = sequence.getSeletor();
        while (!seletor.end()) {
            System.out.println(seletor.current());
            seletor.next();
        }
        //直接new内部类的方法,前提是先有outer class reference
        Sequence_10_2.SequenceSeletor s = sequence.new SequenceSeletor();
    }
}
