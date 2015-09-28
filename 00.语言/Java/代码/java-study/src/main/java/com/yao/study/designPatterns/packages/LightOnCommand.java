package com.yao.study.designPatterns.packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class LightOnCommand implements Command {

    Light ligth;

    public LightOnCommand(Light ligth) {
        this.ligth = ligth;
    }

    @Override
    public void execute() {
        ligth.on();
    }
}
