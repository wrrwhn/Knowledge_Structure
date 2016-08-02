package forkJoin.merge;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  RecursiveTask: 带返回值的执行器
 *
 * Created by Yao on 2015/11/25.
 */
public class MergeMain {

    public static void main(String[] args) {

        int iRows= 100, iCells= 1000;
        String keyWord= "go";

        String[][] doc= new DocMock().GeneDoc(iRows, iCells, keyWord);
        ForkJoinPool pool= new ForkJoinPool();
        DocTask docTask= new DocTask(doc, 0, iRows, keyWord);
        pool.execute(docTask);

        do{
            System.out.printf("MergeMain: {'ActiveThreadCout': %d, 'TaskCount': %d, 'StealCount': %d, 'ParallelismCount': %d }\n", pool.getActiveThreadCount(), pool.getQueuedTaskCount(), pool.getStealCount(), pool.getParallelism());
        }while (!docTask.isDone());

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(docTask.isCompletedNormally()){
            try {
                System.out.printf("Main: The calc count is %d\n", docTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
