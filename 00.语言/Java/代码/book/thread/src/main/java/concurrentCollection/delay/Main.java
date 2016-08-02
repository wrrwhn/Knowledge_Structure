package concurrentCollection.delay;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/4.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int NUM = 5;

        DelayQueue<Event> queue = new DelayQueue<>();
        Thread[] threads = new Thread[NUM];

        for (int i = 0; i < NUM; i++) {
            threads[i] = new Thread(new Task(i, queue));
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: load the data[%s]\n", queue.size());

        int count = 0;
        Event event;
        do {
            do {
                event = queue.poll();
                if (null != event)
                    count++;
            } while (null != event);

            System.out.printf("Main - %s: read %s events\n", new Date(), count);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }
}
