package com.jayce.thinkinginjava;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName LostInfomation_15_7
 * @description
 * @date 2018/10/31 21:50
 */
public class LostInfomation_15_7<T> { 
    Map<String, Class<T>> map = new HashMap<>();

    public void addType(String typename, Class<T> type) {
        map.put(typename, type);
    }

    public T createNew() {
        try {
        Set<String> strings = map.keySet();
        String typename = null;
        for (String s : strings) {
            typename = s;
        }
        return (T) Class.forName(typename).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Quart<Frob> quart = new Quart<>();
        Particle<Long, Integer> particle = new Particle<>();
        System.out.println(Arrays.toString(quart.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(particle.getClass().getTypeParameters()));

        //  验证泛型擦除的存在  结果 , 被擦除了!!!
        List<Integer> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        System.out.println(list1.getClass() == list2.getClass());
        Class<Frob> frobClass = Frob.class;
        Frob[] frobs = (Frob[]) Array.newInstance(frobClass, 8);
        System.out.println(Arrays.toString(frobs));
    }

}

class Frob {
}

class Quart<Q> {
}

class Particle<POSITION, MOMENTUM> {
}
