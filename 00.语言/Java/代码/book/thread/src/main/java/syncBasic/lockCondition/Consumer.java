package syncBasic.lockCondition;

/**
 * Created by Yao on 2015/8/26.
 */
public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            buffer.get();
        }
    }
}
