package forkJoin.cancel;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Yao on 2015/12/10.
 */
public class SearchNumberTask extends RecursiveTask<Integer>{

    private int array[];
    private int start, end;
    private int number;
    private TaskManager manager;

    private final static int NOT_FOUND= -1;

    public SearchNumberTask(int[] array, int start, int end, int number, TaskManager manager) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    @Override
    protected Integer compute() {
        int ret;
        if(end- start> 10){
            ret = launcTasks();
        }else{
            ret = lookForNumber();
        }
        return ret;
    }

    private int launcTasks() {
        int mid= (start+ end)/2;
        SearchNumberTask t1= new SearchNumberTask(array, start, mid, number, manager);
        SearchNumberTask t2= new SearchNumberTask(array, mid+1, end, number, manager);
        manager.addTask(t1);
        manager.addTask(t2);
        t1.fork();
        t2.fork();

        int ret= t1.join();
        if(NOT_FOUND!= ret){
            return ret;
        }
        return t2.join();
    }

    private int lookForNumber() {
        for(int i= start; i<end; i++){
            if(number== array[i]){
                System.out.printf("\tTask: Number %d found at %d\n", number , i);
                manager.cancelTask(this);
                return i;
            }
        }

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return NOT_FOUND;
    }

    public void writeCancelMessage() {
        System.out.printf("\tTask: Cancelled task form %d to %d\n", start, end);
    }
}
