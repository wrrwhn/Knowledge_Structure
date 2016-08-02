package custom.schedule;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/15.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        CustomScheduleThreadPoolExecutor executor = new CustomScheduleThreadPoolExecutor(2);

        System.out.printf("Main.schedule at %s\n", new Date());
        CustomTask task = new CustomTask("Schedule");
        executor.schedule(task, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(3);

        task = new CustomTask("Schedule at fixed");
        System.out.printf("Main.fixed at %s\n", new Date());
        executor.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(15);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.printf("Main.end\n");
    }
}
