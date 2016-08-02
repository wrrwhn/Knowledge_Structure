package basic.daemon;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/4.
 */
public class MainTask {

    public static void main(String[] args) throws InterruptedException {
        Deque<Event> deque= new LinkedBlockingDeque<Event>();

        WriterTask writer= new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            new Thread(writer).start();
        }
        TimeUnit.SECONDS.sleep(1);
        new CleanerTask(deque).start();

//        TimeUnit.SECONDS.sleep(1111);
    }
}
