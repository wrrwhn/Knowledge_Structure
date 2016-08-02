package syncAssit.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 *  CyclicBarrier
 *      功能：同步多个线程，并唤醒所有调用 await() 的线程，类似 CountDownLatch
 *      实现：await(long time, TimeUnit unit)：指定休眠等待时间
 *      特点：
 *          区别于 CountDownLatch ，支持将对象重置回初始状态，即调用 reset() 方法  -> 调用 await() 等待的线程会收到一个 BrokenBarrierException 异常
 *          特殊状态 - 损坏状态：当多个线程在 await() 方法上等待时，其中一个线程中断，则当前纯种抛出 InterruptedException 异常，其它等待线程抛出 BrokenBarrierException 异常。
 */
public class CBMain {

    public static void main(String[] args) {
        final int SIZE= 10000;
        final int LENGTH= 1000;
        final int SEARCH_NUM= 5;
        final int PARTICIPANT_VAL= 5;
        final int LINE_PARTICIPANT= SIZE/ PARTICIPANT_VAL;

        MatrixMock mock= new MatrixMock(SIZE, LENGTH, SEARCH_NUM);
        Results results= new Results(SIZE);
        Grouper grouper= new Grouper(results);
        CyclicBarrier cyclicBarrier= new CyclicBarrier(PARTICIPANT_VAL, grouper);

        for (int i = 0; i < PARTICIPANT_VAL; i++) {
            Searcher searcher = new Searcher(mock, results, i* LINE_PARTICIPANT, (i+1)* LINE_PARTICIPANT, SEARCH_NUM, cyclicBarrier);
            new Thread(searcher).start();
        }
    }
}
