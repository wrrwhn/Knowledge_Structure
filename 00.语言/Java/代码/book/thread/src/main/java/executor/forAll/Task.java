package executor.forAll;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/28.
 */
public class Task implements Callable<Result> {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {

        TimeUnit.SECONDS.sleep((long) (Math.random()* 10));
        Integer value= new Random().nextInt(100);
        System.out.printf("\tTask: %s's value is %d\n", name, value);
        return new Result(name, value);
    }
}
