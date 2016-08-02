package forkJoin.asynchronous;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * FolderTask
 * FolderTask.fork();  将新对象发送到线程池中，当池中有空闲工作者线程或创建新线程时执行任务时，fork() 立即返回
 * FolderTask.join();  主任务完成后调用，用于等待任务执行完毕
 * Main
 * FolderTask.Join();  同上例，亦可使用 FolderTask.get()
 * 注：上例使用了 Fork Join 方法才支持 工作窃取算法
 * 使用 invokeAll 为同步方式
 */
public class Main {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();

        FolderTask bat = new FolderTask("D:\\server", "bat");
//        FolderTask word = new FolderTask("D:\\知识结构\\40.技巧\\沟通\\文档", "docx");
        FolderTask pdf = new FolderTask("D:\\download", "xmind");

        pool.execute(bat);
//        pool.execute(word);
        pool.execute(pdf);

        do {
            System.out.printf("-->-->-->-->-->-->-->-->\n\tMain.Parallelism:%d\n\tMain.ActiveThreadsCount:%d\n\tMain.TaskCount:%d\n\tMain.StealCount:%d\n",
                    pool.getParallelism(), pool.getActiveThreadCount(), pool.getQueuedTaskCount(), pool.getStealCount());
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (
                !bat.isDone()
//                        || !word.isDone()
                        || !pdf.isDone());

        pool.shutdown();

        List<String> list;
        list = bat.join();
        System.out.printf("%d files were found in bat.task\n", list.size());
        System.out.println(list);
//        list = word.join();
//        System.out.printf("%d files were found in word.task\n", list.size());
        list = pdf.join();
        System.out.printf("%d files were found in pdf.task\n", list.size());
    }
}
