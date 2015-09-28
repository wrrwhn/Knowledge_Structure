package com.yao.study.designPatterns.singleton;

/**
 * Created by Yao on 2015/3/15.
 */
public class SyncSingleton {

    private static SyncSingleton singleton;

    //设置构造方法为私有实现
    private SyncSingleton() {
    }

    //使用静态单一接口进行对象创建
    public static synchronized SyncSingleton getInstance(){
        if(null== singleton){
            singleton= new SyncSingleton();
        }
        return singleton;
    }
}
