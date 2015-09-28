package com.yao.study.thread.syncBasic.fairLock;

/**
 * Created by Yao on 2015/8/25.
 */
public class PrintJob implements Runnable {

    private Printer printer;

    public PrintJob(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.print(new Object());
    }
}
