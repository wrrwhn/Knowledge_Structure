package com.yao.study.designPatterns.decorator;

/**
 * Created by Yao on 2015/2/12.
 */
public class CoffeeMaker {

    public static void main(String[] args){
        Beverage beverage= new Espresso();
        System.out.println(beverage.getDescription()+ "\t$:"+ beverage.cost());

        beverage= new HouseBlend();
        beverage= new Mocha(beverage);
        beverage= new DarkRoast(beverage);
        System.out.println(beverage.getDescription()+ "\t$:"+ beverage.cost());
    }
}
