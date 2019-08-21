package com.jayce.algorithmslearn;

import java.util.*;

/**
 * @author: jayce tang
 * @Date: 2018/11/17 10:50
 * @Description:    简单hashMap
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    private static final int SIZE = 997;

    private LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();

        boolean find = false;
        while (it.hasNext()) {
            MapEntry<K, V> ipair = it.next();
            if (ipair.getKey().equals(key)) {
                oldValue = ipair.setValue(value);
                find = true;
                break;
            }
        }

        if (!find) {
            MapEntry<K, V> pair = new MapEntry<>(key, value);
            bucket.add(pair);
        }

        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        for (MapEntry<K, V> me : buckets[index]) {
            if (me.getKey().equals(key)) {
                return me.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> list : buckets) {
            if (list == null) {
                continue;
            }
            for (MapEntry<K, V> me : list) {
                set.add(me);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        for (int i=1; i<101; i++) {
            simpleHashMap.put(i, i+"");
        }
        Iterator<Entry<Integer, String>> iterator = simpleHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class MapEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V result = this.value;
        this.value = value;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MapEntry)) {
            return false;
        }
        MapEntry<K, V> me = (MapEntry<K, V>) o;
        return (key == null ? me.getKey() == null : me.getKey().equals(key))
                && (value == null ? me.getValue() == null : me.getValue().equals(value));
    }

    @Override
    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public String toString() {
        return "MapEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

