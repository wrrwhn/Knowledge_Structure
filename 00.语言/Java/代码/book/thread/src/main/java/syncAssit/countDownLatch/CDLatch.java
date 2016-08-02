package syncAssit.countDownLatch;

/**
 * Created by Yao on 2015/9/23.
 */
public class CDLatch {

    /**
     *  CountDownLatch
     *      作用：用于保证多个任务的同步执行
     *      元素：
     *          初始值，即需要等待执行完成的任务数量
     *          await()方法，用于要求执行方等待其它事件均完成后才能进行下一操作
     *          countDown()方法 ，每个被等待的事件完成后调用通知
     *      特点：
     *          构造对象时，于构造器中初始化内部计数器，并于每次 countDown 调用后将计数器减 1。当计数器的值为 0 时，CountDownLatch将唤醒所有在 await() 上等待的线程。
     *          await(long time, TimeUnit unit)，用于指定等待一定时间后继续执行
     *              TimeUnit -> DAYS/ HOURS/ MICROSECONDS/ MILLISECONDS/ MINUTES/ NANOSECONDS/ SECONDS
     */
    public static void main(String[] args) {

        int arrCnt= 10;
        Arriver arriver;

        MeetingCtr meetingCtr= new MeetingCtr(arrCnt);
        new Thread(meetingCtr).start();

        for (int i = 0; i < arrCnt; i++) {
            arriver= new Arriver(meetingCtr, "person-"+ i);
            new Thread(arriver).start();
        }
    }
}
