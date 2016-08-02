package adapter;

/**
 * Created by Yao on 2015/3/19.
 */
public class MallardDuck implements  Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
