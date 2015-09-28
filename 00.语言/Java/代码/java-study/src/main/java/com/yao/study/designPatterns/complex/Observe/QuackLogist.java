package com.yao.study.designPatterns.complex.Observe;

/**
 * Created by Yao on 2015/5/10.
 */
public class QuackLogist implements IObserver {
    @Override
    public void update(IQuackObservable duck) {
        System.out.println(String.format("QuackLogist: %s just quacked", duck));
    }
}
