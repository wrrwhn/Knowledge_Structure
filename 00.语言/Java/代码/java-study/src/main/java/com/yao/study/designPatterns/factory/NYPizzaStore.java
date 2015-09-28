package com.yao.study.designPatterns.factory;

/**
 * Created by Yao on 2015/3/1.
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        switch (type){
            case "":
                return new NYStyleCheesePizza();
            default:
                return new NYStyleCheesePizza();
        }
    }
}
