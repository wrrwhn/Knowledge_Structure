package complex.Observe;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yao on 2015/5/10.
 */
public class Observable implements IQuackObservable {
    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    IQuackObservable duck;

    public Observable(IQuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        Iterator iterator = observers.iterator();
        while (iterator.hasNext()) {
            IObserver observer = (IObserver) iterator.next();
            observer.update(duck);
        }
    }
}
