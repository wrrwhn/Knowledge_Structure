package com.yao.study.designPatterns.complex.duck;

import com.yao.study.designPatterns.complex.Observe.IObserver;
import com.yao.study.designPatterns.complex.Observe.Observable;

/**
 * Created by Yao on 2015/5/8.
 */
public class RedHeadDuck implements IQuackable {

    Observable observable;

    public RedHeadDuck() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
