package com.yao.study.designPatterns.complex.Observe;


/**
 * Created by Yao on 2015/5/10.
 */
public interface IQuackObservable {

    public void registerObserver(IObserver observer);
    public void notifyObservers();
}
