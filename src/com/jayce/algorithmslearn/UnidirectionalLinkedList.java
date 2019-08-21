package com.jayce.algorithmslearn;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName UnidirectionaLinkedList
 * @description 单向链表
 * @date 2018/10/12 21:19
 */
public class UnidirectionalLinkedList<T> {
    private Node head;
    private static int size;

    public boolean add(T t) {
        if (head == null) {
            head = new Node(t);
            size++;
            return true;
        }
        Node lastNode = head;
        while (lastNode.next()) {
            lastNode = lastNode.next;
        }
        lastNode.next = new Node(t);
        size++;
        return true;
    }

    public void print() {
        Node n = head;
        int count = 1;
        while (n.next()) {
            if (count++ % 10 == 0) {
                System.out.println(n.data.toString() + " ");
            } else {
                System.out.print(n.data.toString() + " ");
            }
            n = n.next;
        }
        System.out.println(n.data.toString());
    }

    class Node {
        private T data;
        private Node next;

        public Node(T t) {
            this.data = t;
        }

        public boolean next() {
            return next != null;
        }
    }

    public static void main(String[] args) {
        UnidirectionalLinkedList<Integer> uni = new UnidirectionalLinkedList<>();
        for (int i=1; i<=39; i++) {
            uni.add(i);
        }

        uni.print();
        uni.linkedListReverse();
        System.out.println();
        uni.print();
    }


    /**
     * linked list reverse
     */
    public void linkedListReverse() {
        /*Node temp1 = head.next;
        Node temp2 = null;
        while (head.next()) {
            head.next = null;
            if (temp1.next()) {
                temp2 = temp1.next;
            }
            temp1.next = head;
            head = temp1;
            temp1 = temp2;
        }*/
        if (head != null) {
            Node temp1 = head;
            if (temp1.next()) {
                Node temp2 = head.next;
                head.next = null;
                if (temp2.next()) {
                    while (temp2.next()) {
                        head = temp2;
                        temp2 = temp2.next;
                        head.next = temp1;
                        temp1 = head;
                    }
                    temp2.next = head;
                    head = temp2;
                } else {
                    temp2.next = head;
                    head = temp2;
                }
            }
            /*int i = 0;
            Node temp1 = head;
            while (i == size) {

                i++;
            }*/
        }
    }
}
