package forkJoin.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by Yao on 2015/12/10.
 */
public class TaskManager {

    private List<ForkJoinTask<Integer>> taskList;

    public TaskManager(){
        taskList= new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task){
        taskList.add(task);
    }

    public void cancelTask(ForkJoinTask<Integer> task){
        for(ForkJoinTask<Integer> t : taskList){
            if(task!= t){
                t.cancel(true);
                ((SearchNumberTask)t).writeCancelMessage();
            }
        }
    }
}
