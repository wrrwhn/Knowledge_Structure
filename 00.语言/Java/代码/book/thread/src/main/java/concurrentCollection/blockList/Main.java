package concurrentCollection.blockList;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 *  阻塞式线程安全列表
 *      LinkedBlockingDeque：
 *          put：阻塞式
 *          add：非阻塞式，超过限定界限后报错
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<String>(3);

        Thread client = new Thread(new ClientTask(deque));
        client.start();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                String req = deque.take();
                System.out.printf("Main: Request: {%s} at %s. Left Size is %d\n", req, new Date(), deque.size());
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }

        System.out.printf("Main finished");
    }

}
