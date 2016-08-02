package forkJoin.exception;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  异常检测：
 *      任务执行中招聘异常，程序不会停止
 *      但在主任务中，可通过 task.isCompletedAbnormally() 立即检测到异常，并可通过 task.getException() 获取到抛出的异常信息
 *
 *
 * Created by Yao on 2015/12/10.
 */
public class Main {

    public static void main(String[] args) {

        int array[] = new int[100];
        ForkJoinPool pool= new ForkJoinPool();
        Task task = new Task(array, 0, 100);

        pool.execute(task);
        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(task.isCompletedAbnormally()){
            System.out.printf("Main: %s\n", task.getException());
        }
        System.out.printf("Main: Result is %d \n", task.join());
    }
}
