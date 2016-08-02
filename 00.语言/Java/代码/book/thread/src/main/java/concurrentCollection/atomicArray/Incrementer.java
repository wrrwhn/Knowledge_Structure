package concurrentCollection.atomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Yao on 2016/3/9.
 */
public class Incrementer implements Runnable {

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        int size = this.array.length();
        for (int i = 0; i < size; i++) {
            this.array.getAndIncrement(i);
        }
    }
}
