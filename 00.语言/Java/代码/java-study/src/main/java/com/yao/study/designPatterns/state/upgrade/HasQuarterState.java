package com.yao.study.designPatterns.state.upgrade;

import java.util.Random;

/**
 * Created by Yao on 2015/4/26.
 */
public class HasQuarterState implements IState {

    GumballMachine gm;
    Random random= new Random();

    public HasQuarterState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("退出硬币");
        gm.setState(gm.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("拉动转轴");
        if(0== random.nextInt(10)){
            gm.setState(gm.getWinnerState());
        }else {
            gm.setState(gm.getSoldState());
        }
        gm.getState().dispense();
    }

    @Override
    public void dispense() {
        System.out.println("请拉动转轴");
    }
}
