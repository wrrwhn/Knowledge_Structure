package com.yao.study.designPatterns.complex;

import com.yao.study.designPatterns.complex.Goose.Goose;
import com.yao.study.designPatterns.complex.Goose.GooseAdapter;
import com.yao.study.designPatterns.complex.Iterator.Flock;
import com.yao.study.designPatterns.complex.Observe.QuackLogist;
import com.yao.study.designPatterns.complex.decorative.QuackCounter;
import com.yao.study.designPatterns.complex.duck.IQuackable;
import com.yao.study.designPatterns.complex.factory.AbstractDuckFactory;
import com.yao.study.designPatterns.complex.factory.CountingDuckFactory;

/**
 * Created by Yao on 2015/5/8.
 */
public class DuckSimulator {

    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();    // 内部以封装模式进行扩展
        simulator.simulate(duckFactory);
    }

    void simulate(AbstractDuckFactory duckFactory) {                //工厂模式进行对象创建
        IQuackable mallardDuck = duckFactory.createMallardDuck();
        IQuackable redHeadDuck = duckFactory.createRedHeadDuck();
        IQuackable duckCall = duckFactory.createDuckCall();
        IQuackable rubberDuck = duckFactory.createRubberDuck();
        Goose goose = new Goose();

        QuackLogist quackLogist= new QuackLogist();
        Flock flockDucks= new Flock(mallardDuck, redHeadDuck, duckCall, rubberDuck,
                new GooseAdapter(goose));   // 组合+ 适配器+ 装饰 实现
        flockDucks.registerObserver(quackLogist);

        System.out.println(
                "\n---------------------------\n" +
                        "鸭子要开始叫啦\n" +
                        "---------------------------"
        );

//        simulate(flockDucks);
        System.out.println(
                String.format("\n---------------------------\n" +
                                "鸭子们的惨叫次数为：%s" +
                                "\n---------------------------",
                        QuackCounter.getQuackCnt()
                )
        );

    }

    void simulate(IQuackable duck) {
        duck.quack();
    }
}
