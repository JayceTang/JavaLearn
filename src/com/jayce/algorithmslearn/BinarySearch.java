package com.jayce.algorithmslearn;

import java.util.Scanner;

/**
 * @author: jayce tang
 * @Date: 2018/11/19 13:46
 * @Description:
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = new int[10000];
        for (int i=0; i<10000; i++) {
            a[i] = ++i;
        }
        Scanner scanner = new Scanner(System.in);
        int max= 10000;
        int min = 0;
        while (true) {
            //  用加法计算的话可能会导致参数溢出   所以需要很巧妙的一些改进
            //  int k = (max + min) / 2;   防止max + min的值溢出！
            int k = min + ((max -min) >> 1);
            System.out.println("你想的数字是" + k + "对吗？是请回答t，不是请回答是l(low低)或h(high高)");
            String answer = scanner.next();
            if  (answer.equals("t")) {
                System.out.println("谢谢您的参与！再见");
                break;
            } else if (answer.equals("l") || answer.equals("low")) {
                min = k+1;

            } else if (answer.equals("h") || answer.equals("high")) {
                max = k-1;
            }
        }


    }
}
