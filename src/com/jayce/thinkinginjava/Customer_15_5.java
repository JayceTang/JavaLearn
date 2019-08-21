package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Customer_15_5
 * @description
 * @date 2018/10/31 7:51
 */
public class Customer_15_5 {
    private static long count = 0;
    private final long id = count++;
    private Customer_15_5(){}
    @Override
    public String toString(){
        return "Customer " + id;
    }

}
