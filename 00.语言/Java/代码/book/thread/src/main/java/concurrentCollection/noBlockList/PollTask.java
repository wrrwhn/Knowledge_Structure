package concurrentCollection.noBlockList;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Yao on 2015/12/26.
 */
public class PollTask implements Runnable {

    private ConcurrentLinkedQueue<String> list;

    public PollTask(ConcurrentLinkedQueue<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.poll();
            list.poll();
        }
    }
}
