package com.jayce.algorithmslearn;

/**
 * @author jayce tang
 * @version 1.0
 * @InterfaceName MyList
 * @description
 * @date 2018/10/14 15:20
 */
public interface MyList<E> {
    boolean add(E e);
    boolean remove(int index);
    boolean remove(E e);
    E get(int index);
}
