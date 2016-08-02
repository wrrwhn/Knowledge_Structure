package forkJoin.exception;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/12/10.
 */
public class Task extends RecursiveTask<Integer> {

    private int array[];
    private int start, end;

    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if(end-start< 10){
            if((3> start) && (3< end))
                throw new RuntimeException(String.format("\t-> Exception from %d to %d", start, end));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            int mid= (start+ end)/2;
            Task t1= new Task(array, start, mid);
            Task t2= new Task(array, mid+ 1, end);
            invokeAll(t1, t2);
        }

        return 0;
    }
}
