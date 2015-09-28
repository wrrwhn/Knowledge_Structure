package com.yao.study.designPatterns.complex.factory;

import com.yao.study.designPatterns.complex.Observe.Observable;
import com.yao.study.designPatterns.complex.duck.*;


/**
 * Created by Yao on 2015/5/9.
 *
 * 直接创建 Duck 对象工厂
 */
public class DuckFactory extends AbstractDuckFactory {

    public DuckFactory() {
    }

    @Override
    public IQuackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public IQuackable createRedHeadDuck() {
        return new RedHeadDuck();
    }

    @Override
    public IQuackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public IQuackable createRubberDuck() {
        return new RubberDuck();
    }
}
