package syncAssit.semaphore;

/**
 * Created by Yao on 2015/8/27.
 */
public class SemaphoreMain {

    /***
     *  Semaphore 为计数器，指定的初始数值即为可提供的资源数量，申请一个则可使用数量减一，当总数为0的时候，则剩余线程则需要等待释放
     *      acquire()：需要捕获 InterruptedException 异常
     *      semaphore.tryAcquire()：尝试获取，立即返回结果
     *      semaphore.acquireUninterruptibly()：忽略线程中断并且不抛出任何异常
     */
    public static void main(String[] args) {
        Printer printer= new Printer();
        for (int i = 0; i < 10; i++) {
            new Thread(new PrintJob(printer)).start();
        }
    }
}
