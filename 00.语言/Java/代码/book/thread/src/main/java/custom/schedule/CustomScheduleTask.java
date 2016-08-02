package custom.schedule;

import java.util.Date;
import java.util.concurrent.*;

/**
 * extends FutureTask<V>：实现了 RunnableScheduleFuture
 * implements RunnableScheduledFuture<V>： 在定时执行器中执行的任务都必须实现
 */
public class CustomScheduleTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {

    private RunnableScheduledFuture<V> task;
    private ScheduledThreadPoolExecutor executor;
    private long period;
    private long startDate;

    public CustomScheduleTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    @Override
    public long getDelay(TimeUnit unit) {

        if (!isPeriodic())
            return task.getDelay(unit);
        else {
            if (0 == startDate)
                return task.getDelay(unit);
            else {
                long delay = startDate - new Date().getTime();
                return unit.convert(delay, TimeUnit.MICROSECONDS);
            }
        }
    }

    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    @Override
    public void run() {

        if (isPeriodic() && !executor.isShutdown()) {
            startDate = new Date().getTime() + period;
            executor.getQueue().add(this);
        }
//        System.out.printf("\tPre-CustomScheduleTask: %s\n", new Date());
        super.runAndReset();
//        System.out.printf("\tPost-CustomScheduleTask: %s\n", new Date());
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
