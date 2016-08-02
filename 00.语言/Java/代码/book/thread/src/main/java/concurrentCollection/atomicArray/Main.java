package concurrentCollection.atomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Yao on 2016/3/9.
 */
public class Main {

    public static void main(String[] args) {

        final int SIZE = 10;

        AtomicIntegerArray array = new AtomicIntegerArray(10);
        Thread increThreads[] = new Thread[SIZE];
        Thread decreThreads[] = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            increThreads[i] = new Thread(new Incrementer(array));
            decreThreads[i] = new Thread(new Decrementer(array));

            increThreads[i].start();
            decreThreads[i].start();
            try {
                increThreads[i].join();
                decreThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < SIZE; i++) {
//            if (0 != array.get(i))
                System.out.printf("\tArray[" + i + "]= %d\n", i, array.get(i));
        }
    }
}
