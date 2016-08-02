package syncAssit.phaser;

import java.util.concurrent.Phaser;

/**
 *  Phaser：跟踪计数每个回合需要执行的线程
 *      方法：
 *          arriveAndAwaitAdvance()：完成本次任务并等待其它任务进行下一阶段任务
 *          arriveAndDeregister()：完成本次任务并跳出队列不再等待其它线程
 *          改变对象：
 *              arrive()：通知一个参与者已经完成，无需等待其它参与者则完成当前阶段
 *              awaitAdvance(int phase)：传入的阶阶段参数与当前一致时则将当前线程置于休眠，直至该阶段所有参与者都运行完成，否则立马返回
 *              awaitAdvanceInterruptibly(int phaser)：同 awaitAdvance ，但作用时休眠线程被中断，并抛出 InterruptedException
 *          参与注册：
 *              register()：添加一新参与者，且设置为未执行完本阶段
 *              bulkRegister(int parties)：批量添加 parties 个线程，且均设置为当前阶段未执行状态
 *          强制终止
 *              forceTermination()：强制将 Phaser 的状态设置为 Termination，主要用于一个参与线程产生错误时
 *
 *      注：默认初始调用 arriveAndAwaitAdvance 方法，则所有线程均会休眠，直到达到初始设定同步线程数后再同时运行
 *
 *      状态：
 *          Active：活跃态，存在参与同步的线程时的状态
 *          Termination：终止态，所有参数同步的线程均取消注册，具体表现为当 onAdvance() 方法返回 true 时终止。
 *              可通过 IsTerminated() 方法确认
 *
 *  注：　Phaser 类不必对它的方法进行异常处理
 */
public class PhaserMain {

    public static void main(String[] args) {

        Phaser phaser= new Phaser(3);
        FileSearch sexJpg= new FileSearch("E:\\图片\\收集\\性感", "jpg", phaser);
        FileSearch sexPng= new FileSearch("E:\\图片\\收集\\性感", "png", phaser);
        FileSearch nakedJpg= new FileSearch("E:\\图片\\收集", "jpg", phaser);

        Thread tSexJpg= new Thread(sexJpg, "JPG-性感图片");
        tSexJpg.start();
        Thread tSexPng= new Thread(sexPng, "PNG-性感图片");
        tSexPng.start();
        Thread tNakedJpg= new Thread(nakedJpg, "JPG-收藏图片");
        tNakedJpg.start();

        try {
            tSexJpg.join();
            tSexPng.join();
            tNakedJpg.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
