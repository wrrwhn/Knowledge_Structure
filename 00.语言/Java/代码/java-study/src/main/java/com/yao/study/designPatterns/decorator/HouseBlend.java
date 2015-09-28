package com.yao.study.designPatterns.decorator;

/**
 * Created by Yao on 2015/2/12.
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description= "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
