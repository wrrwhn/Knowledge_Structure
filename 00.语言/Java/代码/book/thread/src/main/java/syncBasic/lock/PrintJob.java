package syncBasic.lock;

/**
 * Created by Yao on 2015/8/25.
 */
public class PrintJob implements Runnable {

    private PrintQueue printQueue;

    public PrintJob(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s going to print a document \n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s finished the printJob\n", Thread.currentThread().getName());
    }
}
