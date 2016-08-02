package forkJoin.merge;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Yao on 2015/11/25.
 */
public class LineTask extends RecursiveTask<Integer> {

    private final int MIN_SIZE= 100;

    private String[] lines;
    private int start, end;
    private String keyWord;

    public LineTask(String[] lines, int start, int end, String keyWord) {
        this.lines = lines;
        this.start = start;
        this.end = end;
        this.keyWord = keyWord;
    }

    @Override
    protected Integer compute() {
        int result= 0;
        if(end- start< MIN_SIZE){
            result= count(lines, start, end, keyWord);
        }else{
            int mid= (start+ end)/ 2;
            LineTask lt1= new LineTask(lines, start, mid+1, keyWord);
            LineTask lt2= new LineTask(lines, mid+1, end, keyWord);
            invokeAll(lt1, lt2);

            try {
                result= lt1.get()+ lt2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int count(String[] lines, int start, int end, String keyWord) {
        int cnt= 0;
        for(int i= start; i< end && i< lines.length; i++){
            if(keyWord.equals(lines[i])){
                cnt++;
            }
        }
        return cnt;
    }
}
