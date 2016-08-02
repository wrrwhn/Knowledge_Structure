package basic.factory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/17.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Task task= new Task();
        TaskFactory factory= new TaskFactory();

        for (int i = 0; i < 5; i++) {
            factory.newThread(task).start();
        }

        System.out.printf("Factory states:\n%s\n", factory.getStates());
    }
}
