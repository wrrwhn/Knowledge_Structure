package syncAssit.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by Yao on 2015/9/27.
 */
public class Consumer implements Runnable {

    int runCnt= 10;
    int runLen= 20;

    private List<String> data;
    private Exchanger<List<String>> exchanger;

    public Consumer(List<String> data, Exchanger<List<String>> exchanger) {
        this.data = data;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < runCnt; i++) {
            // 当前阶段提醒
            System.out.printf("Consumer in stage %d\n", i+ 1);
            // 数据同步
            try {
                exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取所有数据
            if(data != null && data.size()> 0){
                for (int j = 0; j < runLen; j++) {
                    String event = data.remove(0);
                    System.out.printf("\tConsumer receive data is %s\n", event);
                }
            }
        }
    }
}
