package executor.done;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/30.
 */
public class ExecTask implements Callable<String> {

    private String name;

    public ExecTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String call() throws Exception {
        long duration = (long) (Math.random()* 10);
        System.out.printf("\t%s work for %d seconds\n", this.name, duration);
        TimeUnit.SECONDS.sleep(duration);
        return name+ " finished";
    }
}
