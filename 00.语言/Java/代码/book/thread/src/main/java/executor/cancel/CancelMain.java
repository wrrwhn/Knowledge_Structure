package executor.cancel;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  Future= executor.submit(Callable<?>)
 *      获取执行器执行指定任务的控制器，并通过该元素进行任务的取消和相关操作
 *
 *  方法：
 *      future.cancel(true)： 仅当参数为 true 时可取消正在运行的任务
 *
 *  注：
 *      若任务已完成| 之前已取消| 某原因无法取消，cancel(true)=> false 且任务不能取消
 *      若任务已被取消，则调用 future.get() 时会抛出 CancellationException 异常
 *
 */
public class CancelMain {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();

        System.out.printf("Main start at %s\n", new Date());
        Future<String> future= executor.submit(task);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main canceling  the task\n");
        if(future.cancel(true))
            System.out.printf("Main cancel the task success\n");
        else
            System.out.printf("Main cancel the task fail\n");

        System.out.printf("\tTask state [Cancelled, Done]= [%s, %s]\n", future.isCancelled(), future.isDone());

        executor.shutdown();
        System.out.printf("Main finish\n");

    }
}
