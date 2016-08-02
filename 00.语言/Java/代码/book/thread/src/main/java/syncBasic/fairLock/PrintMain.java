package syncBasic.fairLock;

/**
 *  ReentrantLock& ReentrantReadWriteLock 均含有一个布尔参数 fair
 *      fair= true：公平模式，很多线程等待锁时，会选择等待时间最长的来访问临界区
 *      fair= false：非公平模式，很多线程等等锁时，锁将选择其中一个来访问临界区
 *  注：公平模式只针对 lock() 和 unlock() 方法
 *
 *
 *
 */
public class PrintMain {

    public static void main(String[] args) {

        Printer printer= new Printer();
        for (int i = 0; i < 10; i++) {
            new Thread(new PrintJob(printer)).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
