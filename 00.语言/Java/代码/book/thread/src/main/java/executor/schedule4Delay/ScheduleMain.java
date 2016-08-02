package executor.schedule4Delay;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  延迟执行功能
 *
 *  ScheduledThreadPoolExecutor: 在定时执行器中等待一定时间后执行一个任务
 *      如：本例同时将所有任务发送给执行器，但由于处理器仅一个，则一个等着一个，正好处理的时间间隔为一秒
 *          | - 1
 *          | - - 2
 *          | - - - 3
 *      方法：
 *          executor.schedule(任务， 等待时间， 时间单位)
 *          executor.awaitTermination(1, TimeUnit.DAYS)：在指定时间范围内等待所有任务的处理
 *              注：虽然已经先行调用了 executor.shutdown() ，但由于默认行为为不论执行器是否结束，待处理的任务仍将被执行
 */
public class ScheduleMain {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("Main start at %s\n", new Date());
        for (int i = 0; i < 3; i++) {
            Task task= new Task("task-"+ i);
            executor.schedule(task, i, TimeUnit.SECONDS);
        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
            System.out.printf("Main end at %s\n", new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
