package concurrentCollection.noBlockList;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  非阻塞式线程安全列表
 *      ConcurrentLinkedQueue：操作无法立即执行，即抛出异常或NULL
 */
public class Main {

    private static final int LIST_SIZE= 100;

    public static void main(String[] args) {

        Thread[] threads= new Thread[LIST_SIZE];
        ConcurrentLinkedQueue<String> queue= new ConcurrentLinkedQueue<String>();

        for (int i = 0; i < LIST_SIZE; i++) {
            threads[i]= new Thread(new AddTask(queue));
            threads[i].start();
        }
        for (int i = 0; i < LIST_SIZE; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("AddTAsks has add");

        for (int i = 0; i < LIST_SIZE; i++) {
            threads[i]= new Thread(new PollTask(queue));
            threads[i].start();
        }
        for (int i = 0; i < LIST_SIZE; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PollTasks has add");

        System.out.println("The size of list is "+ queue.size());
    }
}
