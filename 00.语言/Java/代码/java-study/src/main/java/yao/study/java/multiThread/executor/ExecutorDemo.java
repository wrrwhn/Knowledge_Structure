package yao.study.java.multiThread.executor;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/2/5.
 */
public class ExecutorDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //创建服务调用池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        Future<String> future = threadPool.submit(new DemoCallable("1"));
        System.out.println(future.get());

        threadPool.shutdown();

    }
}

class DemoCallable implements Callable<String>{

    private String input;

    public DemoCallable(String input) {
        this.input = input;
    }

    @Override
    public String call() throws Exception {
        return "DemoCallable.input= "+ input;
    }
}
