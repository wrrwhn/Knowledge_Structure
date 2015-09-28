package com.yao.study.designPatterns.encapsulation.initial;

/**
 * Created by Yao on 2015/4/1.
 */
public class Coffee {

    void prepareRecipe(){
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }

    private void addSugarAndMilk() {
        System.out.println("Adding Sugar and Milk");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }

    private void brewCoffeeGrinds() {
        System.out.println("Dripping Coffee through filter");
    }

    private void boilWater() {
        System.out.println("Boiling water");
    }

}



