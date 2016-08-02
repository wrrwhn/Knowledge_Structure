package executor.schedule4Cycle;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  周期执行功能
 *
 *  ScheduledThreadPoolExecutor: 在延迟一段时间执行后，周期进行当前任务的执行操作
 *
 *      方法：
 *          executor.scheduleAtFixedRate(任务 implements Runnable, 首次执行延迟时间, 周期延迟时间, TimeUnit.SECONDS);
 *          注：
 *              如若任务执行时间为 5 秒，但周期执行频率为 3 秒，则执行期间存在两个任务实例的情况
 *              返回的 ScheduledFuture 扩展了 Future 并带有定时任务的相关操作方法 ->  interface ScheduledFuture<V> extends Delayed, Future<V>
 *
 *          executor.scheduleWithFixedDelay：较上者的区别在于，周期时间为两个任务执行间的时间间隔
 *          executor.shutdown(): 默认调用后，定时任务结束
 */
public class ScheduleMain {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("Main start at %s\n", new Date());

        Task task = new Task("task");
        ScheduledFuture<?> sf= executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            System.out.printf("->Main delay %d\n", sf.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        System.out.printf("Main end at %s\n", new Date());
    }
}
