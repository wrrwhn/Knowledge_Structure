package basic.group;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/15.
 */
public class SearchTask  implements  Runnable {

    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name= Thread.currentThread().getName();
        System.out.printf("SearchTask.run - Thread %s Start\n", name);
        try{
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.println("SearchTask.run - doTask error");
        }

        System.out.printf("SearchTask.run - Thread %s End\n", name);
    }

    private void doTask() throws InterruptedException{
        Random random = new Random(new Date().getTime());
        int value= (int)(random.nextDouble()* 10);
        System.out.printf("SearchTask.doTask - Thraed %s Sleep %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String[] args) {
        ThreadGroup group= new ThreadGroup("SearchGroup");
        Result result1= new Result();
        SearchTask task= new SearchTask(result1);

        for (int i = 0; i <5; i++) {
            new Thread(group, task).start();
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Main - sleep error");
            }
        }

        System.out.printf("Main - Number of Threads: %d\nInformation about the Thraed Groupd\n", group.activeCount());
        group.list();   // 打印线程组信息

        listGroupInfo(group);

        waitFinish(group);
        group.interrupt();
    }

    private static void waitFinish(ThreadGroup group) {
        while(group.activeCount()> 4){
            try{
                listGroupInfo(group);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("waitFinish - One Thread is finished!");
            }
        }
    }

    /***
     * 遍历线程组当前活动线程，并打印出该线程的名称和状态
     * @param group
     */
    private static void listGroupInfo(ThreadGroup group){

        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);
        System.out.println("----------------------Thread Group Infos----------------------");
        for (int i = 0; i < group.activeCount(); i++) {
            System.out.printf("thread %s: %s \n", threads[i].getName(), threads[i].getState());
        }
    }
}
