package observer.natively;

/**
 * Created by Yao on 2015/2/1.
 */
public interface Subject {

    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
