package executor.Rejected;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Yao on 2015/10/29.
 */
public class RejectedMain {

    public static void main(String[] args) {

        // 创建执行器，并设置越野处理器，同时启动服务
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(new RejectedTaskHandler());

        System.out.println("Main start");
        for (int i = 0; i < 3; i++) {
            executor.submit(new Task("Task-"+ i));
        }
        System.out.println("Main.Executor shutting down");
        executor.shutdown();

        // 关闭执行器，并添加任务
        executor.submit(new Task("Task-Impossible"));
        System.out.printf("Main end");
    }
}
