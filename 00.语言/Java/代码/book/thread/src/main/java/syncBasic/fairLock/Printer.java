package syncBasic.fairLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yao on 2015/8/25.
 */
public class Printer {

    private Lock lock= new ReentrantLock(true);

    public void print(Object doc){
        lock.lock();
        try{
            Long duration = (long)(Math.random()* 10);
            System.out.printf("%s print\n", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        lock.lock();
        try{
            Long duration = (long)(Math.random()* 10);
            System.out.printf("%s print twice\n", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
