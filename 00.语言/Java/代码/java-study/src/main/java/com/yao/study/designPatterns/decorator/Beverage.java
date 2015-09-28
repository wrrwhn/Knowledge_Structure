package com.yao.study.designPatterns.decorator;

/**
 * 装饰组件
 *
 * Created by Yao on 2015/2/12.
 */
public abstract class Beverage {

    String description= "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
