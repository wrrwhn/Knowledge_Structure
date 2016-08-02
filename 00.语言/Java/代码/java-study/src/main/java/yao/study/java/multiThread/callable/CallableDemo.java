package yao.study.java.multiThread.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2015/2/5.
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> future= new FutureTask<Integer>(new DemoCallable());
        new Thread(future).start();

        System.out.println(future.get());
    }

}

//允许将该线程的结果返回
class DemoCallable implements Callable<Integer>{

    //于新的子线程进行运行
    @Override
    public Integer call() throws Exception {
        System.out.println("CallableDemo.DemoCallable.call");
        return new Random().nextInt(100);
    }
}
