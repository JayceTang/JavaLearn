package com.jayce.thinkinginjava;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName UnboundWildcard2_15_10_3
 * @description
 * @date 2018/11/5 23:54
 */
public class UnboundWildcard2_15_10_3 {
    static Map map1;
    static Map<?, ?> map2;
    static Map<String, ?> map3;
    static void assign1(Map map) {
        map1 = map;
    }
    static void assign2(Map<?, ?> map) {
        map2 = map;
    }
    static void assign3(Map<String, ?> map) {
        map3 = map;
    }

    public static void main(String[] args) {
        assign1(new HashMap());
        assign2(new HashMap());
        assign3(new HashMap());
        assign1(new HashMap<String, Integer>());
        assign2(new HashMap<String, Integer>());
        assign3(new HashMap<String, Integer>());
    }
}
