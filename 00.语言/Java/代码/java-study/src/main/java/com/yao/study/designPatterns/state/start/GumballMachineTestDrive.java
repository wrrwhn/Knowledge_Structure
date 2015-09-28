package com.yao.study.designPatterns.state.start;

/**
 * Created by Yao on 2015/4/26.
 */
public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachine gm= new GumballMachine(5);
        System.out.println(gm);

        gm.insertQuarter();
        gm.turnCrank();
        System.out.println(gm);

        gm.insertQuarter();
        gm.ejectQuarter();
        gm.turnCrank();
        System.out.println(gm);

        gm.insertQuarter();
        gm.turnCrank();
        gm.insertQuarter();
        gm.turnCrank();
        gm.ejectQuarter();
        System.out.println(gm);


        gm.insertQuarter();
        gm.insertQuarter();
        gm.turnCrank();
        gm.insertQuarter();
        gm.turnCrank();
        gm.insertQuarter();
        gm.turnCrank();
        System.out.println(gm);

    }
}
