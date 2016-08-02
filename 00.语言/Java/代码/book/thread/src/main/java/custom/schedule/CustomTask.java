package custom.schedule;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/15.
 */
public class CustomTask implements Runnable {

    private String type;

    public CustomTask(String type) {
        this.type = type;
    }

    @Override
    public void run() {
        try {
            System.out.printf("\t\tTask[%s] at %s\n", this.type, new Date());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
