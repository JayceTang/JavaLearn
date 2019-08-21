package com.jayce.effectivejava;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestLongAndlong
 * @description
 * @date 2018/10/2 21:39
 */
public class TestSamllLongAndBigLong {
    public static void main(String[] args) {
        smallLong();
        bigLong();
    }

    public static long smallLong() {
        long startTime = System.currentTimeMillis();
        long sum = 0L;
        for (long i=0; i<Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("smalLong time: " + (System.currentTimeMillis() - startTime));
        return sum;
    }

    public static long bigLong() {
//        Map<String, Integer> map = new HashMap<>(30);
        long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (long i=0; i<Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("bigLong time: " + (System.currentTimeMillis() - startTime));
        return sum;
    }
}
