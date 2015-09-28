package com.yao.study.designPatterns.state.upgrade;

/**
 * Created by Yao on 2015/4/26.
 */
public class GumballMachine {

    int count= 0;

    IState noQuarterState= new NoQuarterState(this);
    IState hasQuarterState= new HasQuarterState(this);
    IState SoldState= new SoldState(this);
    IState soldOutState= new SoldOutState(this);
    IState winnerState= new WinnerState(this);
    IState state;

    public GumballMachine(int count) {
        this.count = count;
        state= noQuarterState;
    }

    public IState getNoQuarterState() {
        return noQuarterState;
    }

    public IState getHasQuarterState() {
        return hasQuarterState;
    }

    public IState getSoldState() {
        return SoldState;
    }

    public IState getSoldOutState() {
        return soldOutState;
    }

    public IState getWinnerState() {
        return winnerState;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public void releaseBalls(){
        System.out.println("弹出糖果，销售成功");
        count--;
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state.getClass().getName() +
                ", count=" + count +
                '}';
    }
}
