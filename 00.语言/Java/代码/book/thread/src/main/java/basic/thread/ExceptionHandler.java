package basic.thread;

/**
 * Created by Yao on 2015/8/12.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception has been captured");
        System.out.printf("Thread: %s", t.getId());
        System.out.printf("Exception: %s: %s", e.getClass().getName(), e.getMessage());
        System.out.println("Stack Trace: ");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s", t.getState());
    }
}
