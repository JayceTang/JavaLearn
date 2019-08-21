package com.jayce.effectivejava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName NutritionFacts
 * @description  如果 创建的类有使用多个不同参数的构造器,那么考虑先使用构建器模式
 * @date 2018/10/2 18:10
 */

/** Builder pattern */
public class NutritionFacts {
    private final int servingSize;
    private final int serving;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    /** 该静态类甚至可以可以实现一个Builder接口来自动判断它生成那个类型的build方法*/
    public static class Builder implements com.jayce.effectivejava.Builder<NutritionFacts> {
        // required parameter
        private final int servingSize;
        private final int serving;

        // optional parameter  - initialized to default value
        private int calories       = 0;
        private int fat            = 0;
        private int sodium         = 0;
        private int carbohydrate   = 0;

        public Builder(int servingSzie, int serving) {
            this.servingSize = servingSzie;
            this.serving = serving;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }
        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }
        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }
        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }


        @Override
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        serving = builder.serving;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
