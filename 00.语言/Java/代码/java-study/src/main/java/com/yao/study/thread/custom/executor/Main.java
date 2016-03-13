package com.yao.study.thread.custom.executor;

import com.yao.study.thread.custom.threadFactory.CustomFactory;
import com.yao.study.thread.custom.threadFactory.CustomTask;

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
