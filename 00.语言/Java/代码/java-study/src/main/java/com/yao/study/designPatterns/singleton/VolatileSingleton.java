package com.yao.study.designPatterns.singleton;

/**
 * Created by Yao on 2015/3/15.
 */
public class VolatileSingleton {

    //保证线程在每次使用的时候，都会读取变量最后修改的值
    private volatile static VolatileSingleton singleton;

    //设置构造方法为私有实现
    private VolatileSingleton() {
    }

    //使用静态单一接口进行对象创建
    public static VolatileSingleton getInstance() {
        if (null == singleton) {
            synchronized (VolatileSingleton.class) {
                //确保线程内部的变量副本内家是否为空
                if (null == singleton) {
                    singleton = new VolatileSingleton();
                }
            }
        }
        return singleton;
    }
}
