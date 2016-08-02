package com.yao.study.java.multiThread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/2/5.
 */
public class ExecutorPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //创建服务调用池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        //批量创建事件
        for(int i = 1; i < 5; i++){
            callables.add(new DemoPoolCallable(i+ ""));
        }

        //调用所有服务并返回处理结果
        List<Future<String>> futures = threadPool.invokeAll(callables);
        for(Future<String> future: futures){
            System.out.println(future.get());
        }

        //关闭服务
        threadPool.shutdown();
    }
}

class DemoPoolCallable implements Callable<String>{

    private String input;

    public DemoPoolCallable(String input) {
        this.input = input;
    }

    @Override
    public String call() throws Exception {
        return "DemoCallable.input= "+ input;
    }
}