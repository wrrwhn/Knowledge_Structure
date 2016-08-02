package syncBasic.lockCondition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yao on 2015/8/26.
 */
public class Buffer {

    private LinkedList<String> buffer;
    private int maxSize;
    private Lock lock;
    private Condition cWrite;
    private Condition cRead;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;

        buffer= new LinkedList<>();
        lock= new ReentrantLock();
        cWrite = lock.newCondition();
        cRead = lock.newCondition();
        pendingLines= true;
    }

    public void insert(String line){
        lock.lock();

        try{
            while (maxSize== buffer.size()){
                cWrite.await();
            }
            buffer.offer(line);
            System.out.printf("%s insert line(%d)\n", Thread.currentThread().getName(), line.length());
            cRead.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public String get(){
        String tmp= null;
        lock.lock();

        try{
            while (buffer.size()== 0 && hasPendingLines()){
                cRead.await();
            }
            if(hasPendingLines()) {
                tmp = buffer.poll();
                System.out.printf("%s Read line(%d)\n", Thread.currentThread().getName(), tmp.length());
                cWrite.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return tmp;
    }

    public boolean hasPendingLines() {
        return pendingLines|| buffer.size()> 0;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }


}
