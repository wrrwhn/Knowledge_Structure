package state.start;

/**
 * Created by Yao on 2015/4/25.
 */
public class GumballMachine {

    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;

    int state = SOLD_OUT;
    int count = 0;

    /**
     * 初始糖果数量
     * @param count
     */
    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    /**
     * 放入25美分
     */
    public void insertQuarter() {
        switch (state) {
            case HAS_QUARTER:
                System.out.println("You can't insert more quarter");
                break;
            case NO_QUARTER:
                state = HAS_QUARTER;
                System.out.println("You inserted a quarter");
                break;
            case SOLD_OUT:
                System.out.println("can't insert quarter, the machine is empty");
                break;
            case SOLD:
                System.out.println("make a deal and the product is prepare for you");
                break;
            default:
                System.out.println(
                        String.format("something wrong with this situation - [%s]", state)
                );
        }
    }

    /***
     * 尝试退出25美分
     */
    public void ejectQuarter() {
        switch (state) {
            case HAS_QUARTER:
                System.out.println("Quarter returned");
                state = NO_QUARTER;
                break;
            case NO_QUARTER:
                System.out.println("You haven't inserted a quarter");
                break;
            case SOLD_OUT:
                System.out.println("you can't eject, you haven't inserted a quarter");
                break;
            case SOLD:
                System.out.println("Sorry, you already turned the crank");
                break;
            default:
                System.out.println(
                        String.format("something wrong with this situation - [%s]", state)
                );
        }
    }

    /**
     * 尝试转动曲柄
     */
    public void turnCrank(){
        switch (state) {
            case HAS_QUARTER:
                System.out.println("You turned");
                break;
            case NO_QUARTER:
                System.out.println("You turned but there is not quarter");
                break;
            case SOLD_OUT:
                System.out.println("You turned, but there are no gumballs");
                break;
            case SOLD:
                System.out.println("Turning twice dosen't get you another gumball");
                break;
            default:
                System.out.println(
                        String.format("something wrong with this situation - [%s]", state)
                );
        }
    }

    /***
     * 发放糖果
     */
    public void dispense(){
        switch (state) {
            case HAS_QUARTER:
                System.out.println("No gumballl dispendsed");
                break;
            case NO_QUARTER:
                System.out.println("You need to pay frist");
                break;
            case SOLD_OUT:
                System.out.println("No gumballl dispendsed");
                break;
            case SOLD:
                System.out.println("A gumball comes rolling out the slot");
                --count;
                if(0== count){
                    System.out.println("Oops, out of gumballs");
                    state= SOLD_OUT;
                }else{
                    state= NO_QUARTER;
                }
                break;
            default:
                System.out.println(
                        String.format("something wrong with this situation - [%s]", state)
                );
        }
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
