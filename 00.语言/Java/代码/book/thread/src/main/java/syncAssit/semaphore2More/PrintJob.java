package syncAssit.semaphore2More;

/**
 * Created by Yao on 2015/8/27.
 */
public class PrintJob implements Runnable {

    private Printer printer;

    public PrintJob(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.print();
    }
}
