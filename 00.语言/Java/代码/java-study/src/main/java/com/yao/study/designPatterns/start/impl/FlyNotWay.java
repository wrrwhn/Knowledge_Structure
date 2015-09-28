package com.yao.study.designPatterns.start.impl;

import com.yao.study.designPatterns.start.FlyBehavior;

/**
 * Created by Yao on 2015/1/28.
 */
public class FlyNotWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
