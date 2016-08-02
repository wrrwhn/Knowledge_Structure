package state.upgrade;

/**
 * Created by Yao on 2015/4/26.
 */
public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachine gm= new GumballMachine(5);
        System.out.println(gm.getState());

        gm.getState().insertQuarter();
        gm.getState().insertQuarter();
        gm.getState().turnCrank();
        System.out.println(gm);

        gm.getState().insertQuarter();
        gm.getState().ejectQuarter();
        gm.getState().turnCrank();
        System.out.println(gm);

        gm.getState().insertQuarter();
        gm.getState().turnCrank();
        gm.getState().insertQuarter();
        gm.getState().turnCrank();
        gm.getState().ejectQuarter();
        System.out.println(gm);


        gm.getState().insertQuarter();
        gm.getState().insertQuarter();
        gm.getState().turnCrank();
        gm.getState().insertQuarter();
        gm.getState().turnCrank();
        gm.getState().insertQuarter();
        gm.getState().turnCrank();
        System.out.println(gm);

    }
}
