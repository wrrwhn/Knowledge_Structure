package com.yao.study.designPatterns.state.upgrade;

/**
 * Created by Yao on 2015/4/26.
 */
public class NoQuarterState implements IState {
    GumballMachine gm;

    public NoQuarterState(GumballMachine gm) {
        this.gm = gm;
    }
    @Override
    public void insertQuarter() {
        System.out.println("投入硬币");
        gm.setState(gm.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("您尚未投币");
    }

    @Override
    public void turnCrank() {
        System.out.println("请先投币");
    }

    @Override
    public void dispense() {
        System.out.println("请先投币");
    }
}
