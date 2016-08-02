package concurrentCollection.priorityBlockList;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Yao on 2016/3/2.
 */
public class Main {

    public static void main(String[] args) {

        final int THREAD_NUM = 5;
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread[] threads = new Thread[THREAD_NUM];

        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(new Task(i, queue));
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Queue size is %s", queue.size());


        for (int i = 0; i < THREAD_NUM * Task.TASK_NUM; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: priority %d\n", event.getThreadId(), event.getPriority());
        }
        System.out.printf("Main: Queue size is %s", queue.size());
        System.out.println("Main: finish the program!");

    }
}
