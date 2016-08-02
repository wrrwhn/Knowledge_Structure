package com.yao.study.java.multiThread.completion;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/2/5.
 */
public class CompletionDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool= Executors.newCachedThreadPool();
        CompletionService<String> cs = new ExecutorCompletionService<String>(threadPool);

        for(int i=0; i< 10; i++){
            cs.submit(new DemoCallable(i+ ""));
        }

        for(int i=0; i< 10; i++){
            System.out.println(cs.take().get());
        }

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