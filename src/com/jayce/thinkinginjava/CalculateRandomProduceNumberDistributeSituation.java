package com.jayce.thinkinginjava;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName CalculateRandonProduceNumberDistributeSituation
 * @description
 * @date 2018/10/11 21:23
 */
public class CalculateRandomProduceNumberDistributeSituation {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
         Random random = new Random(47);
        for (int i=0; i<10000; i++) {
            int r = random.nextInt(20);
            Integer value = map.get(r);
            map.put(r, value == null ? 1 : value + 1);
        }

        System.out.println(map);
    }
}
