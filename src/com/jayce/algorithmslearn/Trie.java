package com.jayce.algorithmslearn;

/**
 * @author: jayce tang
 * @date: 2018/12/13 11:36
 * @description:        字典树，专门用于字符串匹配的数据结构
 */
public class Trie {
    /** 存储无意义字符 */
    private TrieNode root = new TrieNode('/');

    /** 往 Trie 树中插入一个字符串    */
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
                if (i == text.length-1) {
                    newNode.isEndingChar = true;
                }
            }
            p = p.children[index];
        }
    }

    /** 在 Trie 树中查找一个字符串        */
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                // 不存在 pattern
                return false;
            }
            p = p.children[index];
        }
        /*if (p.isEndingChar == false) return false; // 不能完全匹配，只是前缀
        else return true; // 找到 pattern*/
        return p.isEndingChar;
    }

    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public TrieNode(char data) {
            this.data = data;
        }
    }
}

