package com.yao.study.designPatterns.complex.decorative;

import com.yao.study.designPatterns.complex.Observe.IObserver;
import com.yao.study.designPatterns.complex.Observe.Observable;
import com.yao.study.designPatterns.complex.duck.IQuackable;


/**
 * Created by Yao on 2015/5/8.
 */
public class QuackCounter implements IQuackable {

    Observable observable;
    IQuackable duck;
    static int quackCnt;

    public QuackCounter(IQuackable duck) {
        this.observable = new Observable(this);
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        quackCnt++;
        notifyObservers();
    }

    public static int getQuackCnt() {
        return quackCnt;
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
