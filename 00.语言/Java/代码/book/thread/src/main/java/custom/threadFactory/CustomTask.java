package custom.threadFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/13.
 */
public class CustomTask implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
