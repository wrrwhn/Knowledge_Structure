package com.yao.study.designPatterns.adapter;

/**
 * Created by Yao on 2015/3/21.
 */
public class DuckDrive {

    public static void main(String[] args){

        Duck duck= new MallardDuck();
        duck.quack();
        duck.fly();

        duck= new TurkeyAdapter(new WildTurkey());
        duck.quack();
        duck.fly();
    }
}
