package com.yao.study.thread.executor.completion;

import java.util.concurrent.*;

/**
 * Created by Yao on 2015/10/9.
 */
public class ReportMain {

    public static void main(String[] args) {

        ExecutorService executor= Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<String>(executor);

        System.out.println("ReportMain start");

        Thread first = new Thread(new ReportRequest("first", service));
        Thread second= new Thread(new ReportRequest("second", service));
        Thread third= new Thread(new ReportRequest("third", service));
        ReportProcessor reportProcessor= new ReportProcessor(service);
        Thread process = new Thread(reportProcessor);

        first.start();
        second.start();
        third.start();
        process.start();

        try {
            first.join();
            second.join();
            third.join();
            process.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reportProcessor.setEnd(true);

        System.out.println("ReportMain start");
    }
}
