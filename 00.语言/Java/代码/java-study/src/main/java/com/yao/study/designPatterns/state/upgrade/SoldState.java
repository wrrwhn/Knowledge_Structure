package com.yao.study.designPatterns.state.upgrade;

/**
 * Created by Yao on 2015/4/26.
 */
public class SoldState implements IState {
    GumballMachine gm;

    public SoldState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已成功投币，请拉动转轴");
        gm.setState(gm.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("退出硬币");
        gm.setState(gm.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        this.dispense();
    }

    @Override
    public void dispense() {
        gm.releaseBalls();
        if(gm.count> 0) {
            gm.setState(gm.getNoQuarterState());
        }else{
            gm.setState(gm.getSoldOutState());
        }
    }
}
