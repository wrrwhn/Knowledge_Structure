package executor.forAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  ExecutorService.invokeAll
 *      等待所有的任务完成
 *
 *      注：
 *          参数 Callable<Type> 与返回值 Future<Type> 的 Type 类型需要一致
 *          返回的 Future 均为执行完成状态·
gi j  *
 */
public class InvokeAllMain {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Task> taskList = new ArrayList<>();
        List<Future<Result>> futureList= null;

        for (int i = 0; i < 3; i++) {
            taskList.add(new Task("task-"+ i));
        }

        try {
            futureList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.printf("\nMain: Results below\n");
        for (int i = 0; i < futureList.size(); i++) {
            Future<Result> future= futureList.get(i);
            try {
                Result result= future.get();
                System.out.printf("\t%s-> %d\n", result.getName(), result.getValue());
            } catch (InterruptedException |ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
