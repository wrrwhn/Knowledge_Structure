package concurrentCollection.delay;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * Created by Yao on 2016/3/4.
 */
public class Task implements Runnable {

    private final int SIZE = 100;

    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {

        Date delay = new Date();
        delay.setTime(delay.getTime() + id * 1000);
        System.out.printf("\tThread %s.startTime= %s\n", id, delay);

        for (int i = 0; i < SIZE; i++) {
            queue.add(new Event(delay));
        }
    }
}
