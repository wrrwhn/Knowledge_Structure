package basic.daemon;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/4.
 */
public class WriterTask implements Runnable {

    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i <5; i++) {
            System.out.printf("Thread(%s) add Event, List Count= %s \n", Thread.currentThread().getId(), deque.size());
            Event event = new Event(Thread.currentThread().getId()+ "");
            this.deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
