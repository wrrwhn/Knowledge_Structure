package executor.completion;

import java.util.concurrent.*;

/**
 *  CompletionService
 *      作用：完成任务后自动存储 Future 对象于队列中，供获取处理
 *      方法：
 *          poll()：检查完成队列，有的话返回第一个元素，否则返回 null
 *          take()：类似 poll() 但若队列中无结果则将一直阻塞
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

        reportProcessor.setEnd(true);
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
