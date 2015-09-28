package com.yao.study.designPatterns.factory;

/**
 * Created by Yao on 2015/3/1.
 */
public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        switch (type){
            case "cheese":
                return new ChicagoStyleCheesePizza();
            default:
                return new ChicagoStyleCheesePizza();
        }
    }
}
