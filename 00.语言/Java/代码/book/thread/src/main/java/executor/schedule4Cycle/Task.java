package executor.schedule4Cycle;

import java.util.Date;

/**
 * Created by Yao on 2015/9/29.
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("\t%s start at %s\n", name, new Date());
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
