package executor.schedule4Delay;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by Yao on 2015/9/29.
 */
public class Task implements Callable<String> {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("\t%s start at %s\n", name, new Date());
        return "callBack";
    }
}
