package syncAssit.introduce;

/**
 * Created by Yao on 2015/8/27.
 */
public class Start {

    /***
     *  基本同步
     *      synchronized 关键字
     *      Lock 接口与其实现类，如 ReentrantLock/ ReentrantReadWriteLock.ReadLock/ ReentrantReadWriteLock.WriteLock
     *
     *  高级同步
     *      Semaphore：直译为信号量，为计数器，保护一个或多个共享资源
     *      CountDownLatch：完成一组正在其它线程中执行的操作之前，它允许线程一直等待
     *      CyclicBarrirer：允许多个线程在某个集合点处进行相互等待
     *      Phaser：将并发任务分成多个阶段进行，在开始下一阶段之前，当前阶段中的所有线程都必须完成
     *      Exchanger：提供两个线程间的数据交换点
     */
    public static void main(String[] args) {
        System.out.println("Hello, Thread Assitant");
    }
}
