package com.yao.study.designPatterns.decorator;

/**
 * Created by Yao on 2015/2/12.
 */
public class DarkRoast extends CondimentDecorator {

    Beverage beverage;

    public DarkRoast(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+ ", DarkRoast";
    }

    @Override
    public double cost() {
        return 1.02+ beverage.cost();
    }
}
