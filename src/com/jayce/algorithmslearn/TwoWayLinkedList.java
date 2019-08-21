package com.jayce.algorithmslearn;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TwoWayLinkedList
 * @description 双向链表
 * @date 2018/10/12 21:21
 */
public class TwoWayLinkedList<E> implements MyList<E> {
    private static int size = 0;
    private Node<E> head;
    private Node<E> tail;

    public void print() {
        if (head != null) {
            if (tail == null) {
                System.out.println(head.element.toString());
            } else {
                Node<E> node = head;
                while (node.next()) {
                    System.out.println(node.previous == null ? "null" + " "
                            + node.element.toString() + " " + node.next.element.toString() : node.previous.element.toString() + " "
                            + node.element.toString() + " " + node.next.element.toString());
                    node = node.next;
                }
                System.out.println(node.previous.element.toString() + " " + node.element.toString() + " null");
            }
        } else {
            System.out.println("no everything");
        }
    }

    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node(e);
        if (null == head) {
            head = newNode;
            size++;
            return true;
        }
        if (null == tail) {
            tail = newNode;
            tail.previous = head;
            head.next = tail;
            size++;
            return true;
        }
        newNode.previous = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        return true;
    }

    @Override
    public boolean remove(E e) {
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        Node<E> node = head;
        while (node.next()) {
            if (count == index) {
                return node.element;
            }
            node = node.next;
            count++;
        }
        return node.element;
    }

    private class Node<E> {
        private E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        private boolean next() {
            return next != null;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }
    }

    public static void main(String[] args) {
        TwoWayLinkedList<Integer> twoWayLinkedList = new TwoWayLinkedList();
        twoWayLinkedList.print();
        twoWayLinkedList.add(1);
        twoWayLinkedList.print();
        twoWayLinkedList.add(2);
        twoWayLinkedList.print();
        twoWayLinkedList.add(3);
        twoWayLinkedList.print();
        System.out.println(twoWayLinkedList.size());
        System.out.println(twoWayLinkedList.get(0).toString());
        System.out.println(twoWayLinkedList.get(1).toString());
        System.out.println(twoWayLinkedList.get(2).toString());
        System.out.println(twoWayLinkedList.get(3).toString());
        twoWayLinkedList.get(1);
    }

}
