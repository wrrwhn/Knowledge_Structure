package syncAssit.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Yao on 2015/9/24.
 */
public class Searcher implements Runnable {

    private MatrixMock mock;
    private Results results;
    private int firstRow;
    private int lastRow;
    private int num;
    private CyclicBarrier cyclicBarrier;

    public Searcher(MatrixMock mock, Results results, int firstRow, int lastRow, int num, CyclicBarrier cyclicBarrier) {
        this.mock = mock;
        this.results = results;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.num = num;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        System.out.printf("\t-->%s searcher mock from line %d to %d\n", Thread.currentThread().getName(), firstRow, lastRow);

        for (int i = firstRow; i < lastRow; i++) {
            int data[]= mock.getRow(i);
            int cnt= 0;

            for (int j = 0; j < data.length; j++) {
                if(data[j]== num){
                    cnt++;
                }
            }

            results.setData(i, cnt);
        }

        System.out.printf("\t<--%s finished search\n", Thread.currentThread().getName());

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
