package custom.executor;

import custom.threadFactory.CustomFactory;
import custom.threadFactory.CustomTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/13.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        CustomFactory factory= new CustomFactory("Executor");
        ExecutorService executor= Executors.newCachedThreadPool(factory);

        CustomTask task= new CustomTask();
        executor.submit(task);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
