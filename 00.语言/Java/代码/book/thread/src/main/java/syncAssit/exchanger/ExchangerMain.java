package syncAssit.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 *
 * Exchanger：用于同步两个线程指定数据结构的数据，且【仅能支持两个】线程
 *
 */
public class ExchangerMain {

    public static void main(String[] args) {
        List<String> data = new ArrayList<String>();
        Exchanger<List<String>> exchanger= new Exchanger<List<String>>();

        new Thread(new Producer(data, exchanger)).start();
        new Thread(new Consumer(data, exchanger)).start();
    }
}
