package state.upgrade;

/**
 * Created by Yao on 2015/4/26.
 */
public class SoldOutState implements IState {
    GumballMachine gm;

    public SoldOutState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        System.out.println("糖果已卖完，请勿投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("请先投币");
    }

    @Override
    public void turnCrank() {
        System.out.println("糖果已卖完");
    }

    @Override
    public void dispense() {
        System.out.println("糖果已经卖完");
    }
}
