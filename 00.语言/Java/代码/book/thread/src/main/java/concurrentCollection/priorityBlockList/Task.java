package concurrentCollection.priorityBlockList;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Yao on 2016/3/2.
 */
public class Task implements Runnable {

    public static final int TASK_NUM= 5;

    private int id;
    private PriorityBlockingQueue<Event> queue;

    public Task() {
    }

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < TASK_NUM; i++) {
            this.queue.add(new Event(id, i));
        }
    }
}
