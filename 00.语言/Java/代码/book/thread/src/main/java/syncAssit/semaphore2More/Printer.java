package syncAssit.semaphore2More;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yao on 2015/8/27.
 */
public class Printer {

    private int iCnt= 3;

    private boolean[] printerStatus;
    private final Semaphore semaphore;
    private Lock lockPriters;

    public Printer() {
        semaphore= new Semaphore(3);
        printerStatus= new boolean[iCnt];
        for (int i = 0; i < iCnt; i++) {
            printerStatus[i]= true;
        }
        lockPriters= new ReentrantLock();
    }

    public void print(){
        try{
            semaphore.acquire();
            System.out.printf("--->%s wait for printer. \n", Thread.currentThread().getName());
            int pIdx= getPrinter();
            long duration= (long)(Math.random()* 5);
            System.out.printf("<---%s print %d seconds. \n", Thread.currentThread().getName(), duration);
            TimeUnit.SECONDS.sleep(duration);
            printerStatus[pIdx]= true;
            System.out.printf("\t%d printer finished\n", pIdx);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    /**
     * 返回当前空闲的打印机进程
     * @return
     */
    public int getPrinter(){
        int idx= -1;

        try{
            lockPriters.lock();
            for (int i = 0; i < iCnt; i++) {
                if(printerStatus[i]){
                    idx= i;
                    System.out.printf("\t%d printer is send to use\n", idx);
                    printerStatus[i]= false;
                    break;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lockPriters.unlock();
        }

        return idx;
    }
}
