package start.impl;

import start.QuackBehavior;

/**
 * Created by Yao on 2015/1/28.
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Mute Quack");
    }
}
