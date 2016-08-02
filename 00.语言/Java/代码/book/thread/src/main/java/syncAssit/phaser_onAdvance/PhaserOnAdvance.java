package syncAssit.phaser_onAdvance;

/**
 *
 *  ExamPhaser 继承重写了 Phaser.onAdvance 方法，用于返回确认当前并发是否终止
 *
 */
public class PhaserOnAdvance {

    public static void main(String[] args) {
        int stuCnt= 5;
        ExamPhaser phaser = new ExamPhaser();
        Student[] students= new Student[stuCnt];
        Thread[] threads = new Thread[stuCnt];

        for (int i = 0; i < stuCnt; i++) {
            students[i]= new Student(phaser);
            phaser.register();
        }

        for (int i = 0; i < stuCnt; i++) {
            threads[i]= new Thread(students[i], "student-"+ i);
            threads[i].start();
        }

        for (int i = 0; i < stuCnt; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
