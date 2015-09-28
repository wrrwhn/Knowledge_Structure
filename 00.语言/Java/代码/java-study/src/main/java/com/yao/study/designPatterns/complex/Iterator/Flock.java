package com.yao.study.designPatterns.complex.Iterator;

import com.yao.study.designPatterns.complex.Observe.IObserver;
import com.yao.study.designPatterns.complex.Observe.Observable;
import com.yao.study.designPatterns.complex.duck.IQuackable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yao on 2015/5/10.
 */
public class Flock implements IQuackable {

    Observable observable;
    ArrayList<IQuackable> quackers = new ArrayList<IQuackable>();

    public Flock(IQuackable... quackableArray) {
        for (int i = 0; i < quackableArray.length; i++) {
            quackers.add(quackableArray[i]);
        }
    }

    @Override
    public void quack() {
        Iterator iterator = quackers.iterator();
        while (iterator.hasNext()) {
            IQuackable quacker = (IQuackable) iterator.next();
            quacker.quack();
        }
    }

    @Override
    public void registerObserver(IObserver observer) {
        for (int i = 0; i < quackers.size(); i++) {
            quackers.get(i).registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < quackers.size(); i++) {
            quackers.get(i).notifyObservers();
        }
    }
}
