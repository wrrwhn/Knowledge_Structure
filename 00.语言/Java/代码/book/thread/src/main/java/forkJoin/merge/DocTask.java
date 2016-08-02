package forkJoin.merge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Yao on 2015/11/25.
 */
public class DocTask extends RecursiveTask<Integer> {

    private final int MIN_SIZE= 10;

    private String[][] doc;
    private int start, end;
    private String keyWord;

    public DocTask(String[][] doc, int start, int end, String keyWord) {
        this.doc = doc;
        this.start = start;
        this.end = end;
        this.keyWord = keyWord;
    }

    @Override
    protected Integer compute() {

        int result= 0;

        if(end- start< MIN_SIZE){
            result= process(doc, start, end, keyWord);
        }else{
            int mid= (start+ end)/ 2;
            DocTask t1= new DocTask(doc, start, mid+ 1, keyWord);
            DocTask t2= new DocTask(doc, mid+1, end, keyWord);
            invokeAll(t1, t2);
            try {
                result= t1.get()+ t2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private int process(String[][] doc, int start, int end, String keyWord) {
        List<LineTask> taskList= new ArrayList<LineTask>();
        for (int i = start; i <end && i< doc.length ; i++) {
            taskList.add(new LineTask(doc[i], 0, doc[i].length, keyWord));
        }

        invokeAll(taskList);

        int result= 0;
        for(LineTask task : taskList){
            try {
                result+= task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
