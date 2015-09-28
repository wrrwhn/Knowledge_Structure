package com.yao.study.designPatterns.complex.duck;

import com.yao.study.designPatterns.complex.Observe.IQuackObservable;

/**
 * Created by Yao on 2015/5/8.
 */
public interface IQuackable extends IQuackObservable{

    public void quack();
}
