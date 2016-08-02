package concurrentCollection.noBlockList;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Yao on 2015/12/26.
 */
public class AddTask implements Runnable {

    private ConcurrentLinkedQueue<String> list;

    public AddTask(ConcurrentLinkedQueue<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name= Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name+ i);
        }
    }
}
