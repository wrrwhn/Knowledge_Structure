package observer.java;


/**
 * Created by Yao on 2015/2/1.
 */
public class WeatherStation {

    public static void main(String[] args){
        WeatherData data = new WeatherData();
        CurrentConditionsDisplay cur= new CurrentConditionsDisplay(data);

        data.setMeasurements(1, 2, 3);
        data.setMeasurements(11, 12, 13);
        data.setMeasurements(11, 22, 33);
    }
}
