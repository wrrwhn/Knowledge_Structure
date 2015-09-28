package com.yao.study.designPatterns.factory.reconstruction;

/**
 * Created by Yao on 2015/3/11.
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
