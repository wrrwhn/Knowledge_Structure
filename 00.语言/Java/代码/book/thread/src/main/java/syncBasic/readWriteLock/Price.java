package syncBasic.readWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Yao on 2015/8/25.
 */
public class Price {

    private double priceA;
    private double priceB;
    private ReadWriteLock lock;

    public Price() {
        priceA= 1.0;
        priceB= 2.0;
        lock= new ReentrantReadWriteLock();
    }

    public double getPriceA() {
        lock.readLock().lock();
        double tmp= priceA;
        lock.readLock().unlock();
        return tmp;
    }

    public double getPriceB() {
        lock.readLock().lock();
        double tmp= priceB;
        lock.readLock().unlock();
        return tmp;
    }

    public void set(double priceA, double priceB){
        lock.writeLock().lock();
        this.priceA= priceA;
        this.priceB= priceB;
        lock.writeLock().unlock();
    }


}
