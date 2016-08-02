package concurrentCollection.skipList;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Yao on 2016/3/8.
 */
public class Task implements Runnable {

    private final int NUM = 10;
    private String id;
    private ConcurrentSkipListMap<String, Contact> map;

    public Task(String id, ConcurrentSkipListMap<String, Contact> map) {
        this.id = id;
        this.map = map;
    }

    public Task() {

    }

    @Override
    public void run() {

        for (int i = 0; i < NUM; i++) {
            map.put(id + i, new Contact(i, String.valueOf(1000 + i)));
        }
    }
}
