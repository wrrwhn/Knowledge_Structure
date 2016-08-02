package syncAssit.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by Yao on 2015/9/27.
 */
public class Producer implements Runnable {

    int runCnt= 10;
    int runLen= 20;

    private List<String> data;
    private Exchanger<List<String>> exchanger;

    public Producer(List<String> data, Exchanger<List<String>> exchanger) {
        this.data = data;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < runCnt; i++) {
            // 当前阶段提示
            System.out.printf("Producer in stage %d\n", i+ 1);
            // 指插入数量
            for (int j = 0; j < runLen; j++) {
                String event= "Event-"+ i+ "-"+ j;
                data.add(event);
            }
            // 数据同步
            try {
                exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 显示信息
            System.out.printf("Producer push datas and the length of data is %d\n", data.size());
        }
    }
}
