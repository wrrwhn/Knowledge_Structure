package forkJoin.base;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/11/25.
 */
public class Main {

    /**
     *  作用：
     *      使用分治技术将大任务拆解为小任务进行处理
     *  组成：
     *      ForkJoinPool：实现 ExecutorService 接口和工作窃取算法
     *      ForkJoinTask：ForkJoinPool 中执行的任务基类
     *      RecursiveAction：无 返回结果场景
     *      RecursiveTask：  有 返回结果场景
     *  方法：
     *      execute(RunnableTask)：异步执行 Runnable 但即不采用工作窃取算法
     *      invoke(ForkJoinTask<T> task)：同步调用
     *      adapt()：接收 Runnable 或 Callable 对象，并转化为 ForkJoinTask 对象后执行
     *  限制：
     *      任务只能使用 fork() 和 join() 操作进行同步，若使用其它同步机制，则工作者线程不能执行其它任务
     *          任务不能进行 IO操作
     *          任务不能抛出非运行时异常
     *  备注：
     *      ForkJoinPool可看为特殊的执行器，区别在于使用了工作窃取算法(Work-Stealing Algorithm)
     *          进程切换，线程并发，多个线程执行速度不一样，可让执行完成的线程获取其它未完成线程任务来进行
     *      使用 Join 操作让一个主任务等待它创建的子任务完成，执行该任务线程亦称之为 工作者线程
     *
     * @param args
     */
    public static void main(String[] args) {

        // 初始化对象并执行
        ForkJoinPool pool= new ForkJoinPool();
        List<Product> proList= ProductFactory.Generator(10000);
        Task task= new Task(proList, 0, proList.size(), 0.2);
        pool.execute(task);

        // 输出执行器运行情况
        do{
            System.out.printf("Main: {'ThreadCount': %d, 'StealCount': %d, 'ParallelismCount': %d}\n", pool.getActiveThreadCount(), pool.getStealCount(), pool.getParallelism());
            try{
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!task.isDone());

        // 关闭执行器并校验是否执行完全
        pool.shutdown();
        if(task.isCompletedNormally()){
            System.out.printf("Main: the computer process finished!\n");
        }

        // 校验结果
        int cnt = 0;
        for (int i = 0; i < proList.size(); i++) {
            Product pro= proList.get(i);
            if(pro.getPrice()!= 12){
                cnt ++;
                System.out.printf("\t\t -> Main: {'name': '%s', 'price': %d}\n", pro.getName(), pro.getPrice());
            }
        }
        System.out.printf("Main: %d product is not change\n", cnt);
        System.out.println("Main: finished the program!\n");
    }
}
