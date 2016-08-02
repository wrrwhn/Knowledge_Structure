package state.upgrade;

/**
 * Created by Yao on 2015/4/29.
 */
public class WinnerState implements IState {
    GumballMachine gm;

    public WinnerState(GumballMachine gm) {
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
        switch (gm.count) {
            case 0:
                gm.setState(gm.getSoldOutState());
                break;
            case 1:
                gm.releaseBalls();
                gm.setState(gm.getSoldOutState());
                break;
            case 2:
            default:
                gm.releaseBalls();
//                System.out.println("\t*★,°*:.☆\\(￣▽￣)/$:*.°★* 【恭喜你中奖了】 *★,°*:.☆\\(￣▽￣)/$:*.°★*");
                gm.releaseBalls();
                break;
        }
    }
}
