package forkJoin.base;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Yao on 2015/11/25.
 */
public class Task extends RecursiveAction {

    private static final long serialVersionUID= 1L;
    private final int MAX_LENGTH= 10;

    private List<Product> proList;
    private int start;
    private int end;
    private double increment;

    public Task(List<Product> proList, int start, int end, double increment) {
        this.proList = proList;
        this.start = start;
        this.end = end;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if(end- start< MAX_LENGTH){
            updatePrice();
        }else{
            int middle= (start+ end)/ 2;
//            System.out.printf("\tTask: not executed subtask count is %d\n", getQueuedTaskCount());
            Task t1= new Task(proList, start, middle+ 1, increment);
            Task t2= new Task(proList, middle+ 1, end, increment);
            invokeAll(t1, t2);
        }
    }

    private void updatePrice() {
        for (int i = start; i < end; i++) {
            Product pro= proList.get(i);
            pro.setPrice(pro.getPrice()* (1+ increment));
        }
    }
}
