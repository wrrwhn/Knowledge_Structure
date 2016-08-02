package concurrentCollection.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Yao on 2016/3/9.
 */
public class Account {

    private AtomicLong blance;

    public Account() {
        blance = new AtomicLong();
    }

    public void setBlance(long blance) {
        this.blance.set(blance);
    }

    public long getBlance() {
        return this.blance.get();
    }

    public void add(long amount) {
        this.blance.getAndAdd(amount);
    }

    public void substract(long amount) {
        this.blance.getAndAdd(-amount);
    }
}
