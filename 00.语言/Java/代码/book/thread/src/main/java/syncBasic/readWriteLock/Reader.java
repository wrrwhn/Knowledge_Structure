package syncBasic.readWriteLock;

/**
 * Created by Yao on 2015/8/25.
 */
public class Reader implements Runnable {

    private  Price price;

    public Reader(Price price) {
        this.price = price;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s - [%g, %g]\n", Thread.currentThread().getName(), price.getPriceA(), price.getPriceB());
        }
    }
}
