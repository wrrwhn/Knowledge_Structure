package factory.simple;

/**
 * Created by Yao on 2015/2/13.
 */
public class ClamPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("ClamPizza prepare.");
    }

    @Override
    public void bake() {
        System.out.println("ClamPizza bake.");
    }

    @Override
    public void cut() {
        System.out.println("ClamPizza cut.");
    }

    @Override
    public void box() {
        System.out.println("ClamPizza box.");
    }
}
