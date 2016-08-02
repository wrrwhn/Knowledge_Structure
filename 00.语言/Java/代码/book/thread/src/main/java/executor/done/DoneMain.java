package executor.done;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  FutureTask <= RunnableFuture <- Runnable, Future
 *      done() 方法于任务执行结束时自动调用
 *
 */
public class DoneMain {

    public static void main(String[] args) {

        int taskCnt= 5;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        DoneFuture[] futures= new DoneFuture[taskCnt];

        // 添加 TaskCnt 个任务
        for (int i = 0; i < taskCnt; i++) {
            ExecTask task= new ExecTask("Task["+ i+"]");
            futures[i]= new DoneFuture(task);
            executor.submit(futures[i]);
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 休眠 5 秒后取消未完成任务
        for (int i = 0; i < taskCnt; i++) {
            futures[i].cancel(true);
        }

        // 输出所有顺利完成的任务信息
        System.out.printf("Main Result:\n");
        for (int i = 0; i < taskCnt; i++) {
            DoneFuture future= futures[i];
            if(!future.isCancelled()){
                try {
                    System.out.printf("\t%s\n", future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        // 处理完成后关闭执行器
        executor.shutdown();
    }
}
