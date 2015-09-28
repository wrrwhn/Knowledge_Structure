package com.yao.study.designPatterns.packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class Light {

    private String locate;

    public Light(String locate) {
        this.locate = locate;
    }

    public void on() {
        System.out.println("Light on");
    }

    public void off(){
        System.out.println("Light off");
    }

    @Override
    public String toString() {
        return "Light{" +
                "locate='" + locate + '\'' +
                '}';
    }
}
