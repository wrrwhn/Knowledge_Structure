package com.yao.study.thread.custom.threadPoolExecutor;

import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Yao on 2016/3/9.
 */
public class CustomExecutor extends ThreadPoolExecutor {

    private ConcurrentHashMap<String, Date> map;

    public CustomExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.printf("->->->CustomExecutor.shutdown: Executed(%d), Running(%d), Pending(%d)\n", getCompletedTaskCount(), getActiveCount(), getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("->->->->CustomExecutor.shutdownNow: Executed(%d), Running(%d), Pending(%d)\n", getCompletedTaskCount(), getActiveCount(), getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("->CustomExecutor.beforeExecute: task(%s:%s)\n", t.getName(), r.hashCode());
        map.put(String.valueOf(r.hashCode()), new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;
        Date startTime = map.get(String.valueOf(r.hashCode()));

        Assert.notNull(result);
        Assert.notNull(startTime);

        try {
            System.out.printf("->->CustomExecutor.afterExecute: [%s: %s]\n", result.get().toString(), new Date().getTime() - startTime.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.afterExecute(r, t);
    }
}
