package com.jayce.multithread;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName ImproveList
 * @description     put if absent by composition, precondition all add synchronized
 * @date 2019/4/28 20:05
 */
@ThreadSafe
public class ImproveList<E> implements List<E> {

    private final List<E> list;

    public ImproveList(List<E> list) {
        this.list = list;
    }

    /**
     * 返回假则是存在，没有添加成功；返回真则是不存在，添加成功！
     * @param element
     * @return
     */
    public synchronized boolean putIfAbsent(E element) {
        boolean contains = list.contains(element);
        if (!contains) {
            list.add(element);
        }
        return !contains;
    }

    @Override
    public synchronized int size() {
        return 0;
    }

    @Override
    public synchronized boolean isEmpty() {
        return false;
    }

    @Override
    public synchronized boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
