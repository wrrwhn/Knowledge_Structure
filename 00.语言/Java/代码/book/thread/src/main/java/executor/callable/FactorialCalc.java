package executor.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/27.
 */
public class FactorialCalc implements Callable<Integer> {

    private Integer num;

    public FactorialCalc(Integer num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {

        Integer res= 1;

        for (int i = 2; i <= num; i++) {
            res*= i;
            TimeUnit.MILLISECONDS.sleep(i);
        }

        System.out.printf("\tFactorialCalc: %d!= %d\n", num, res);
        return res;
    }
}
