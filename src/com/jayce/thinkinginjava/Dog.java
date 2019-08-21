package com.jayce.thinkinginjava;

import java.util.Objects;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Dog
 * @description
 * @date 2018/9/17 1:26
 */
public class Dog {
    private int weight;
    private int height;

    public Dog(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        /*if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return weight == dog.weight &&
                height == dog.height;*/
        if (o instanceof Dog) {
            Dog d = (Dog) o;
            return this.height == d.height && this.weight == d.weight;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, height);
    }
}
