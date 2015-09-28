package com.yao.study.designPatterns.appearance;

/**
 * Created by Yao on 2015/4/1.
 */
public class Amplifier implements IDevice{
    @Override
    public void on() {
        System.out.println("Amplifier ON");
    }

    @Override
    public void off() {
        System.out.println("Amplifier Off");
    }
}
