package com.jayce.effectivejava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName NutritionFactsClientCall
 * @description  实现对NutritionFacts类的调用
 * @date 2018/10/2 18:34
 */
public class NutritionFactsClientCall {
    public static void main(String[] args) {
        NutritionFacts n = new NutritionFacts.Builder(240, 8)
                .calories(1).carbohydrate(1).fat(1).sodium(1).build();
    }
}
