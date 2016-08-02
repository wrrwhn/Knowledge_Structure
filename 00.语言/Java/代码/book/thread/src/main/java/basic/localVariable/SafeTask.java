package basic.localVariable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/8/13.
 */
public class SafeTask implements Runnable {

    /***
     * ThreadLocal：线程局部变量，为各线程存储各自属性值
     *      get()：读取属性值
     *      set()：设置属性值
     *      initialValue()：第一次使用时调用进行初始值设定
     * InheritableThreadLocal：区别于 ThreadLocal ，主要特点在于会将属性值传递给子进程
     *      childValue()：可通过覆盖该方法实现子线程变量属性值
     */
    private static ThreadLocal<Date> startDate= new ThreadLocal<Date>(){
        protected Date initialValue(){
            return new Date();
        }
    };

    @Override
    public void run() {

        System.out.printf("Thread-%s Started at %s\n", Thread.currentThread().getId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random()* 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread-%s finished at %s\n", Thread.currentThread().getId(), startDate.get());
    }


    public static void main(String[] args) {
        UnsageTask unsageTask= new UnsageTask();

        for (int i = 0; i < 10; i++) {
            new Thread(unsageTask).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
