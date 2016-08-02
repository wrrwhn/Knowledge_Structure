package start.impl;

import start.Duck;

/**
 * Created by Yao on 2015/1/28.
 */
public class MiniDuck extends Duck {

    public MiniDuck() {
        this.setFlyBehavior(new FlyWithWings());
        this.setQuackBehavior(new MuteQuack());
    }

    public static void main(String[] args){
        Duck duck= new MiniDuck();
        duck.swim();
        duck.performFly();
        duck.performQuack();
        duck.setFlyBehavior(new FlyNotWay());   //动态改变行为
        duck.performFly();
    }
}
