package com.yao.study.designPatterns.appearance;

/**
 * Created by Yao on 2015/4/1.
 */
public class Tuner implements IDevice{
    @Override
    public void on() {
        System.out.println("Tuner On");
    }

    @Override
    public void off() {
        System.out.println("Tuner Off");
    }
}
