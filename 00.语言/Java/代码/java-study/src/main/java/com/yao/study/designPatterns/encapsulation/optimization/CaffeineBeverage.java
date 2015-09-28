package com.yao.study.designPatterns.encapsulation.optimization;

/**
 * Created by Yao on 2015/4/1.
 */
public abstract class CaffeineBeverage {

    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    protected abstract void addCondiments();

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }

    protected abstract void brew();

    void boilWater(){
         System.out.println("Boling water");
     }
}
