package basic.error;

/**
 * Created by Yao on 2015/8/17.
 */
public class ErrorGroup extends ThreadGroup {
    public ErrorGroup(String name) {
        super(name);
    }

    /***
     *  进行不可捕获异常的处理
     *  定位非捕获异常处理器
     *      1.当前异常处理器
     *      2.所在线程组的非捕获异常处理器
     *      3.默认的非捕获异常处理器
     *      4.堆栈信息直接打印至控制台后退出
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the reset of the threads\n");
        interrupt();
    }
}
