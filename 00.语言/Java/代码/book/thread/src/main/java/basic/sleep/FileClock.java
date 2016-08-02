package basic.sleep;

import java.util.Date;

/**
 * Created by Yao on 2015/7/29.
 */
public class FileClock implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <10; i++) {
            System.out.printf("%s\n", new Date());
            try{
                Thread.sleep(500);  // 子进程主动休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread task = new Thread(new FileClock());
        task.start();
        try{
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}
