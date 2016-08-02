package syncAssit.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/27.
 */
public class Printer {

    private final Semaphore semaphore;

    public Printer() {
        semaphore= new Semaphore(2);
    }

    public void print(){
        try{
            semaphore.acquire();
            long duration= (long)(Math.random()* 5);
            System.out.printf("%s print %d seconds. \n", Thread.currentThread().getName(), duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
