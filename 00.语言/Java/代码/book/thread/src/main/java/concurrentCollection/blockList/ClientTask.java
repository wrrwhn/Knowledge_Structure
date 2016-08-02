package concurrentCollection.blockList;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/12/26.
 */
public class ClientTask implements Runnable {

    private LinkedBlockingDeque<String> list;

    public ClientTask(LinkedBlockingDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuffer sb= new StringBuffer();
                sb.append(i).append(":").append(j);
                try {
                    list.putLast(sb.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Client: send [%s] at %s\n", sb, new Date());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Client finish the data change \n");
    }
}
