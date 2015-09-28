package com.yao.study.designPatterns.observer.natively.impl;

import com.yao.study.designPatterns.observer.natively.Observer;
import com.yao.study.designPatterns.observer.natively.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yao on 2015/2/1.
 */
public class WeatherData implements Subject {

    private List<Observer> observerList;
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer observer) {
        if(null== observerList){
            observerList= new ArrayList<Observer>();
        }
        observerList.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observerList.indexOf(observer);
        if (i >= 0) {
            observerList.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observerList.size(); i++) {
            Observer observer = observerList.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementChange() {
        notifyObservers();
    }


    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChange();
    }

}