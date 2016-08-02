package syncAssit.phaser_onAdvance;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/27.
 */
public class Student implements Runnable {

    private Phaser phaser;
    private int exerCnt= 3;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        for (int i = 0; i < exerCnt; i++) {
            doExercise(i, phaser);
        }
    }

    private void doExercise(int i, Phaser phaser) {
        System.out.printf("\t->%s is going for the %s\n", Thread.currentThread().getName(), i!= exerCnt? "exercise "+ i: "last exercise");
        try{
            TimeUnit.SECONDS.sleep((long) (Math.random()* 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("\t<-%s has finish the %s\n", Thread.currentThread().getName(), i!= exerCnt? "exercise "+ i: "exam");
        phaser.arriveAndAwaitAdvance(); // 因为重写了 onAdvance ，当阶段超出考试阶段后便会自动识别为终止态，所以结束考试状态下不需要移除
    }
}
