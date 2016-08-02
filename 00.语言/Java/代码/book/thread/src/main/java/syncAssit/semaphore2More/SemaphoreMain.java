package syncAssit.semaphore2More;

/**
 * Created by Yao on 2015/8/27.
 */
public class SemaphoreMain {

    /***
     *  Semaphore 用于控制整体打印机总体控制
     *  lockPriters 用于具体打印机的分配和相关权限的控制
     */
    public static void main(String[] args) {
        Printer printer= new Printer();
        for (int i = 0; i < 10; i++) {
            new Thread(new PrintJob(printer)).start();
        }
    }
}
