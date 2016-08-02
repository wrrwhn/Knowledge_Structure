package adapter;

/**
 * Created by Yao on 2015/3/21.
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Wild turkey gobble");
    }

    @Override
    public void fly() {
        System.out.println("Wild turkey fly");
    }
}
