package executor.FixedPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  newFixedThreadPool
 *      固定大小的线程执行器，当发送任务超过最大值，则不再创建额外的进程，剩下的任务均阻塞直至执行器有空闲的线程。
 *      用以保证执行器不会给应用程序带来性能上的压力。
 *  newSingleThreadExecutor
 *      创建单线程的执行器
 */
public class Server {

    private ThreadPoolExecutor executor;

    public Server() {
        executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
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
