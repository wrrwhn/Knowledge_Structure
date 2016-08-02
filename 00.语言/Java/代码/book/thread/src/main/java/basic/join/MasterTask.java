package basic.join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/4.
 */
public class MasterTask implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(String.format("%s - 进入线程：%s", new Date(),Thread.currentThread().getName()));
            TimeUnit.SECONDS.sleep(1);
            System.out.println(new Date()+ " - 线程任务完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread master= new Thread(new MasterTask(), "MasterTask");
        Thread minor= new Thread(new MasterTask(), "MinorTask");
        master.start();
        minor.start();

        try{
            /**
             * Join 将指定线程并入当前调用线程，仅当指定线程完成后，当前线程才能完成
             *  join()：仅当任务完成，所挂靠的线程终以结束
             *  join(long milliseconds)：被挂靠线程等待 milliseconds 或限定时间内挂靠线程完成，join 方法均会返回
             *  join(long milliseconds, int nanos)：相对 join(long milliseconds) 额外等待 nanos 毫秒
             *
             */
            master.join();
            minor.join();
            System.out.println("所有任务均已完成");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
