package syncAssit.phaser_onAdvance;

import java.util.concurrent.Phaser;

/**
 * Created by Yao on 2015/9/27.
 */
public class ExamPhaser extends Phaser {

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (this.getPhase()){
            case 0:
                return studentArrive();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean finishExam() {
        System.out.printf("Phaser: The Exam is finished\n");
        return false;
    }

    private boolean finishSecondExercise() {
        System.out.printf("Phaser: The second exercise is finished and left %d student.\n", this.getRegisteredParties());
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.printf("Phaser: The first exercise is finish and left %d student.\n", this.getRegisteredParties());
        return false;
    }

    private boolean studentArrive() {
        System.out.printf("Phaser: The exam is going to start, and %d student arrive.\n", this.getRegisteredParties());
        return false;
    }
}
