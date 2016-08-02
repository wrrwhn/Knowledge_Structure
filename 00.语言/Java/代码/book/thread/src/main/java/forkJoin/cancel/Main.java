package forkJoin.cancel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  取消正在执行的任务
 *      cancel(true)：即使任务正在进行也将被取消
 *          -> cancel() = 如果任务正在运行或执行结束，任务不能被取消，将返回 false
 */
public class Main {

    public static void main(String[] args) {

        int size= 1000;
        int array[] = ArrayGenerator.GenerateArray(size);
        TaskManager manager= new TaskManager();
        SearchNumberTask task = new SearchNumberTask(array, 0, size, 5, manager);

        ForkJoinPool pool= new ForkJoinPool();
        pool.execute(task);

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: finished\n");
    }
}
