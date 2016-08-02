package custom.schedule;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/15.
 */
public class CustomScheduleThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    public CustomScheduleThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    /**
     * 根据参数创建 CustomScheduleTask 对象
     *
     * @param runnable
     * @param task
     * @param <V>
     * @return
     */
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        return new CustomScheduleTask<>(runnable, null, task, this);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> future = super.scheduleAtFixedRate(command, initialDelay, period, unit);
        CustomScheduleTask task = (CustomScheduleTask) future;
        task.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
        return task;
    }
}