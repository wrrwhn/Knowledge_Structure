package observer.java;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yao on 2015/2/5.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    Observable observable;
    private float temperature;
    private float humidity;
    private float pressure;


    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay{" +
                "pressure=" + pressure +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}');
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData data= (WeatherData) o;
            this.temperature= data.getTemperature();
            this.humidity= data.getHumidity();
            this.pressure= data.getPressure();
            display();
        }
    }
}
