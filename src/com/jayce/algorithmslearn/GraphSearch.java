package com.jayce.algorithmslearn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: jayce tang
 * @date: 2018/12/5 13:54
 * @description:    图的广度优先搜索(Breadth_First_Search)和深度优先搜索(Depth_First_Search)
 * question：给你一个用户，如何找出这个用户的所有三度（其中包含一度、二度和三度）好友关系？
 *          以无向图为例，但有向图也是一样的
 */
public class GraphSearch {
    /** 顶点的个数   */
    private int v;
    /** 邻接表     adjacency table */
    private LinkedList<Integer>[] adj;

    public GraphSearch (int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 无向图一条边存两次
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**  breadth_first_search   感觉这个比跳表容易理解多了   - - */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i=0; i<v; i++) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i=0; i<adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    /**
     * 递归打印 s->t的路径
     */
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }

    /**
     *  depth first search
     */
    public void dfs(int s, int t) {

    }
}
