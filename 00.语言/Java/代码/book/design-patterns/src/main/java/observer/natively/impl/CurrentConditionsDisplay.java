package observer.natively.impl;


/**
 * Created by Yao on 2015/2/1.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private Subject weatherData;
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void display() {
        System.out.println( "CurrentConditionsDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}');
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData= weatherData;
        weatherData.registerObserver(this);
    }
}
