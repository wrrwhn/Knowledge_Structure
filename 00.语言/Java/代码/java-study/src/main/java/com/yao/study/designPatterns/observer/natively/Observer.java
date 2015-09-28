package com.yao.study.designPatterns.observer.natively;

/**
 * Created by Yao on 2015/2/1.
 */
public interface Observer {

    /***
     * 进行天气相关数值更新
     * @param temp      温度
     * @param humidity  温度
     * @param pressure  气压
     */
    public void update(float temp, float humidity, float pressure);
}
