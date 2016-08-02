package custom.threadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Yao on 2016/3/13.
 */
public class CustomFactory implements ThreadFactory {

    private int counter;
    private String prefix;

    public CustomFactory(String prefix) {
        this.prefix = prefix;
        this.counter= 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new CustomThread(r, prefix+ "-"+ this.counter++);
    }

    public int getCounter() {
        return counter;
    }
}
