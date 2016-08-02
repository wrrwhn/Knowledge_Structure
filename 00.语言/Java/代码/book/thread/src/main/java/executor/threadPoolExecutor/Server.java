package executor.threadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  ThreadPoolExecutor
 *      作用：
 *          缓存线程池，减少新线程创建花费的时间
 *      创建：
 *          推荐使用 Executors 工厂类创建，如本例的 Executors.newCachedThreadPool()
 *      方法：
 *          执行器信息：
 *              getPoolSize()：返回池中实际线程数
 *              getActiveCount()：返回正在执行任务的线程数
 *              getCompletedTaskCount()：返回已经执行完成的任务数
 *
 *              execute(task)：执行任务
 *              shutdown()：显示结束
 *                  当调用后再行执行任务，执行器会拒绝任务，并抛出 RejectedExecutionException 异常
 *
 *      注：
 *          当发送过多任务给执行器时，系统负荷将过载
 */
public class Server {

    private ThreadPoolExecutor executor;

    public Server() {
        executor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(WebTask task){
        executor.execute(task);
        System.out.printf("Server: [Pool size, Active Task, Completed Task]-> [%d, %d, %d]\n", executor.getPoolSize(), executor.getActiveCount(), executor.getCompletedTaskCount());
    }

    public void endServer(){
        executor.shutdown();
    }

    public static void main(String[] args) {
        Server server= new Server();
        for (int i = 0; i < 10; i++) {
            server.executeTask(new WebTask("Web-Task-"+ i));
        }
        server.endServer();
    }
}
