package com.yao.study.designPatterns.complex.factory;

import com.yao.study.designPatterns.complex.duck.IQuackable;

/**
 * Created by Yao on 2015/5/9.
 */
public abstract class AbstractDuckFactory {

    public abstract IQuackable createMallardDuck();
    public abstract IQuackable createRedHeadDuck();
    public abstract IQuackable createDuckCall();
    public abstract IQuackable createRubberDuck();
}
