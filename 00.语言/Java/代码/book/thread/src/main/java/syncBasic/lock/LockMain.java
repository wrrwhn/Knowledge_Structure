package syncBasic.lock;

/**
 *  使用 ReentrantLock 创建实现，并保证同一时间只有一个执行线程访问这个临界区
 *      注：在离开时要调用 unlock 进行解锁，否则会导致死锁
 *       ReentrantLock 允许进行递归调用，即获得锁的进程进行递归，则针继续持有锁
 *
 *      Lock：获取锁，若未获取则休眠直到获取到锁
 *      TryLock：立刻返回锁获取的情况，若无则则可直接跳过对临界区的操作
 *
 *
 */
public class LockMain {
    public static void main(String[] args) {
        PrintQueue printQueue= new PrintQueue();

        for (int i = 0; i < 10; i++) {
            new Thread(new PrintJob(printQueue)).start();
        }
    }
}
