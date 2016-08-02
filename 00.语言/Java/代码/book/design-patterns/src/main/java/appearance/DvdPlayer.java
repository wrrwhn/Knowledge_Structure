package appearance;

/**
 * Created by Yao on 2015/4/1.
 */
public class DvdPlayer implements IDevice{


    @Override
    public void on() {
        System.out.println("Dvd On");
    }

    @Override
    public void off() {
        System.out.println("Dvd Off");
    }
}
