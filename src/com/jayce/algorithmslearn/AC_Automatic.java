package com.jayce.algorithmslearn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: jayce tang
 * @date: 2018/12/14 10:30
 * @description:
 *     Ac自动机（Aho-Corasick automatic）  多模式串匹配主串（可用于筛选敏感词汇，并把敏感词汇改成 *** ，或者检测到敏感词汇后直接不给提交）
 */
public class AC_Automatic {
    private AcNode root = new AcNode('/');

    /** 往 Trie 树中插入一个字符串    */
    public void insert(char[] text) {
        AC_Automatic.AcNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                AC_Automatic.AcNode newNode = new AC_Automatic.AcNode(text[i]);
                p.children[index] = newNode;
                if (i == text.length-1) {
                    newNode.isEndingChar = true;
                }
            }
            p = p.children[index];
        }
    }

    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    public void match(char[] text) { // text 是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                // 失败指针发挥作用的地方
                p = p.fail;
            }
            p = p.children[idx];
            // 如果没有匹配的，从 root 开始重新匹配
            if (p == null) {
                p = root;
            }
            AcNode tmp = p;
            // 打印出可以匹配的模式串
            while (tmp != root) {
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }


    public class AcNode {
        public char data;
        /** 字符集只包含 a~z 这 26 个字符     */
        public AcNode[] children = new AcNode[26];
        /** 结尾字符为 true      */
        public boolean isEndingChar = false;
        /** 当 isEndingChar=true 时，记录模式串长度   */
        public int length = -1;
        /** 失败指针    */
        public AcNode fail;
        public AcNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

    }

}
