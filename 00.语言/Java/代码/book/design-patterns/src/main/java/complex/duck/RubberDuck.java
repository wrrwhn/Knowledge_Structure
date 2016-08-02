package complex.duck;

/**
 * Created by Yao on 2015/5/8.
 */
public class RubberDuck implements IQuackable {

    Observable observable;

    public RubberDuck() {
        this.observable = new Observable(this);
    }
    @Override
    public void quack() {
        System.out.println("Squeak");
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
