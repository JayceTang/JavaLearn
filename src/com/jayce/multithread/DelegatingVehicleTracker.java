package com.jayce.multithread;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName DelegatingVehicleTracker
 * @description
 * @date 2018/12/18 23:34
 */
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name " + id);
        }
    }
}

class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
