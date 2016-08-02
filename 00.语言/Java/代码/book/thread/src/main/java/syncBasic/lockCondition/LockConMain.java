package syncBasic.lockCondition;

/**
 *  Lock.Condition.await()：线程将自动释放这个条件绑定的锁，其它线程才获取这个锁并执行相同的操作，或执行这个锁保护的另一个临界区代码
 *  Lock.Condition.signal()|signallAll()：一个或多个在该条件上扶起的线程被唤醒，但需要使用 while 循环确认条件成功与否来决定是否调用 await（)
 *  Lock.Condition.awaitUninterruptibly()：不可中断，仅可由其它纯种调用 signal() | signallAll() 来唤起
 *
 *  备注
 *      await(long time, TimeUnit unit)，如下情况发生前，纯种处于休眠状态
 *          1> 其它某个线程中断当前线程
 *          2> 某它某个纯种调用当前线程挂起的条件的 signal() 或 gignalAll() 方法
 *          3> 指定等待时间完成
 *        注： DAYS/ HOURS/ MICROSECONDS/ MILLISECONDS/ MINUTES/ ANOSECONDS/ SECONDS
 *
 */
public class LockConMain {
    public static void main(String[] args) {
        FileMock mock= new FileMock(100, 10);
        Buffer buffer= new Buffer(20);

        new Thread(new Producer(mock, buffer), "Producer").start();
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(buffer), "Consumer-"+ i).start();
        }
    }
}
