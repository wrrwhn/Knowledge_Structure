package executor.threadPoolExecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/27.
 */
public class WebTask implements Runnable {

    private Date date;
    private String name;

    public WebTask(String name) {
        this.date= new Date();
        this.name = name;
    }

    @Override
    public void run() {
        Date start= new Date();
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random()* 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("\tWebTask: %s inited ad %s, started at %s, finished at %s\n", Thread.currentThread().getName(), date, start, new Date());
    }
}
