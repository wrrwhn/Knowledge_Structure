package basic.thread;

/**
 * Created by Yao on 2015/8/12.
 */
public class ExceptionTest {

    /***
     * 异常类型
     *  非运行时异常：须在方法声明的 Throws 语句指定或者方法体内捕获，如 IOException 和 ClassNotFoundException
     *  运行时异常：不必也无法于方法体中捕获，如 NumberFormatException
     *
     *  线程对象异常捕获器
     *      1.查找线程对象的未捕获异常处理器
     *      2.JVM 自动定位线程组的未捕获异常处理器
     *      3.JVM 继续查找默认的未捕获异常处理器
     *      4.均无，则 JVM 将堆栈异常记录打印到控制台，并退出程序
     * @param args
     */
    public static void main(String[] args) {
        Task task= new Task();
        Thread thread= new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler()); // 设置出现异常时处理逻辑
        thread.start();
    }
}
