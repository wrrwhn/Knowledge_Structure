package com.yao.study.designPatterns.complex.factory;

import com.yao.study.designPatterns.complex.decorative.QuackCounter;
import com.yao.study.designPatterns.complex.duck.*;

import java.util.Observable;

/**
 * Created by Yao on 2015/5/9.
 * <p/>
 * 创建封装过后的 Duck 对象工厂
 */
public class CountingDuckFactory extends AbstractDuckFactory {

    @Override
    public IQuackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    public IQuackable createRedHeadDuck() {
        return new QuackCounter(new RedHeadDuck());
    }

    @Override
    public IQuackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    public IQuackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
