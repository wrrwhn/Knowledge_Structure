package basic.error;

import java.util.Random;

/**
 * 当除数为 0 时，自动产生不可捕获的异常
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            result = 1000 / (int) (random.nextDouble() * 1000);
            System.out.printf("%s: %d \n", Thread.currentThread().getId(), result);
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }


    public static void main(String[] args) {
        ErrorGroup group= new ErrorGroup("ErrorTest");
        Task task= new Task();
        for (int i = 0; i < 2; i++) {
            new Thread(group, task).start();
        }
    }
}
