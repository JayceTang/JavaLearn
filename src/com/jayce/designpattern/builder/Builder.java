package com.jayce.designpattern.builder;

/**
 * 建造者模式
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/6 16:36
 * @description
 */
public class Builder {
    public static void main(String[] args) {
        DeliciousStore ds = new DeliciousStore();
        Meal breakfast = ds.createDelicious(new Breakfast());
        Meal lunch = ds.createDelicious(new Lunch());
        System.out.println("小明breakfast吃的是" + breakfast.getFood() + "和" + breakfast.getDrink());
        System.out.println("小明lunch吃的是" + lunch.getFood() + "和" + lunch.getDrink());
    }
}

class Meal {
    private String food;
    private String drink;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}

interface BuildDelicious {
    void setFood();
    void setDrink();
    Meal createMeal();
}

class Breakfast implements BuildDelicious {

    private Meal meal;

    public Breakfast() {
        meal = new Meal();
    }

    @Override
    public void setFood() {
        meal.setFood("油条");
    }

    @Override
    public void setDrink() {
        meal.setDrink("豆浆");
    }

    @Override
    public Meal createMeal() {
        return meal;
    }
}

class Lunch implements BuildDelicious {

    private Meal meal;

    public Lunch() {
        meal = new Meal();
    }

    @Override
    public void setFood() {
        meal.setFood("炒粉");
    }

    @Override
    public void setDrink() {
        meal.setDrink("骨汤");
    }

    @Override
    public Meal createMeal() {
        return meal;
    }
}

/**
 * 定义一个餐点
 * description： 导演者
 */
class DeliciousStore {
    public Meal createDelicious(BuildDelicious bd) {
        bd.setFood();
        bd.setDrink();
        return bd.createMeal();
    }
}
