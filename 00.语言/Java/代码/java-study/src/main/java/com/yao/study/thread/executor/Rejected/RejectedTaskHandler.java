package com.yao.study.thread.executor.Rejected;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Yao on 2015/10/22.
 */
public class RejectedTaskHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("\t", r.toString(), executor.toString(), executor.isTerminating(), executor.isTerminated(), executor.isShutdown());
    }
}
