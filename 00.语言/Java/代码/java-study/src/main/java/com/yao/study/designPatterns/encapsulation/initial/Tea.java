package com.yao.study.designPatterns.encapsulation.initial;

/**
 * Created by Yao on 2015/4/1.
 */
public class Tea {

    void prepareRecipe(){
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    private void addLemon() {
        System.out.println("Adding Lemon");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }

    private void steepTeaBag() {
        System.out.println("Steeping the tea");
    }

    private void boilWater() {
        System.out.println("Boling water");
    }
}
