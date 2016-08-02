package executor.completion;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/10/9.
 */
public class ReportGenerator implements Callable<String> {

    private String name;
    private String title;

    public ReportGenerator(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        long duration = (long) (Math.random() * 10);
        System.out.printf("\tReportGenerator[%s: %s] finish after %d seconds\n", this.name, this.title, duration);
        TimeUnit.SECONDS.sleep(duration);
        return this.name + "-" + this.title;
    }
}
