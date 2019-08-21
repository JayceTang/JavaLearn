package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @Date: 2018/11/24 09:52
 * @Description:    二叉查找树
 */
public class BinarySearchTree {

    private Node head;

    public Node find(int data) {
        Node p = head;
        while (p != null) {
            if (data > p.data) {
                p = p.right;
            } else if (data < p.data) {
                p = p.left;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node p = head;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data) {
        //  指向要删除的节点，初始化为指向根节点
        Node p = head;
        //  p的父节点
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }

        //  要删除的节点有两个子节点
        if (p.left != null && p.right != null) {
            //  找到右子树上最小的，来替换，因为是最小的，所以肯定是叶子节点，所有还需要获取其父节点来删除该最小节点
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        //  pp 为null ，表示删除的是根节点
        if (pp == null) {
           head = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

    }

    public Node findMax() {
        if (head == null) {
            return null;
        }
        Node p = head;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    public Node findMin() {
        if (head == null) {
            return null;
        }

        Node p = head;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    private class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
