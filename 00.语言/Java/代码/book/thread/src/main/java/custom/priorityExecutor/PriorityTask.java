package custom.priorityExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/13.
 */
public class PriorityTask implements Runnable, Comparable<PriorityTask> {

    private String name;
    private int priority;

    public PriorityTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(PriorityTask o) {

        if (this.getPriority() > o.getPriority())
            return -1;
        else if (this.getPriority() < o.getPriority())
            return 1;
        else
            return 0;
    }

    @Override
    public void run() {
        System.out.printf("\tTask[%s, %d] is working\n", this.name, this.priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
