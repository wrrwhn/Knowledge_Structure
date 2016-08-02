package concurrentCollection.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Yao on 2016/3/8.
 */
public class Task implements Runnable {

    final int NUM = 10;

    public Task() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < NUM; i++) {
            System.out.printf("%s.%d\n", threadName, ThreadLocalRandom.current().nextInt(NUM));
        }
    }
}
