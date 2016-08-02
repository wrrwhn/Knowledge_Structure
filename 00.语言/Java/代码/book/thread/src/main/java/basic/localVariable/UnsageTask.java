package basic.localVariable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/12.
 */
public class UnsageTask implements Runnable {

    Date startDate;

    @Override
    public void run() {
        startDate= new Date();
        System.out.printf("Thread-%s Started at %s\n", Thread.currentThread().getId(), startDate);
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random()* 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread-%s finished at %s\n", Thread.currentThread().getId(), startDate);
    }

    public static void main(String[] args) {

        UnsageTask unsageTask= new UnsageTask();

        for (int i = 0; i < 10; i++) {
            new Thread(unsageTask).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
