package com.yao.study.designPatterns.complex.Goose;

import com.yao.study.designPatterns.complex.Observe.IObserver;
import com.yao.study.designPatterns.complex.Observe.Observable;
import com.yao.study.designPatterns.complex.duck.IQuackable;

/**
 * Created by Yao on 2015/5/8.
 */
public class GooseAdapter implements IQuackable {

    Goose goose;
    Observable observable;


    public GooseAdapter(Goose goose) {
        this.goose = goose;
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        goose.honk();
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
