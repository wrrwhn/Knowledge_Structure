package syncBasic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yao on 2015/8/25.
 */
public class PrintQueue {
    private final Lock queueLock= new ReentrantLock();

    public void printJob(Object document){
        queueLock.lock();
        if(true) {
            try {
                Long duration = (long) (Math.random() * 10);
                System.out.printf("\t%s is printing a job during %d seconds\n", Thread.currentThread().getName(), duration);
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }else{
            System.out.printf("\t->%s print task is fail\n", Thread.currentThread().getName());
        }
    }
}
