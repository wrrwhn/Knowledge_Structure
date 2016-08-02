package executor.Rejected;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/10/29.
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.printf("\tTask %s starting\n", name);
            long duration = (long) (Math.random()* 10);
            System.out.printf("\tTask.run.%s -> sleep %d seconds\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
