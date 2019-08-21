package com.jayce.multithread.futuretask;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName ProductInfo
 * @description
 * @date 2019/4/29 23:47
 */
public class ProductInfo {
    private final int id;
    private final String userName;

    public ProductInfo(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
