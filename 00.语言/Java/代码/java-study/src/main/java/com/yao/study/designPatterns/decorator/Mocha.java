package com.yao.study.designPatterns.decorator;

/**
 * Created by Yao on 2015/2/12.
 *
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+ ", Mocha";
    }

    @Override
    public double cost() {
        return .20+ beverage.cost();
    }
}
