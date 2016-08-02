package syncBasic.readWriteLock;

/**
 * Created by Yao on 2015/8/25.
 */
public class Writer implements Runnable {

    private Price price;

    public Writer(Price price) {
        this.price = price;
    }

    @Override
    public void run() {
        double priceA= Math.random()* 27, priceB= Math.random()* 99;
        price.set(priceA, priceB);
        System.out.printf("\t%s set [%g, %g]\n", Thread.currentThread().getName(), priceA, priceB);
    }
}
