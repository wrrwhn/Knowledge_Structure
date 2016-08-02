package start.impl;

import start.QuackBehavior;

/**
 * Created by Yao on 2015/1/28.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
