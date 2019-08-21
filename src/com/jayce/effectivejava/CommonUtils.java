package com.jayce.effectivejava;

import java.util.HashMap;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName CommonUtils
 * @description
 * @date 2018/10/2 16:40
 */
public class CommonUtils {

    /**
     * let it not instance
     */
    private CommonUtils(){}

    public static <K,V> HashMap<K,V> newHashMapInstance() {
        return new HashMap<>();
    }
}
