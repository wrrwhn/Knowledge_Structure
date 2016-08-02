package executor.completion;

import java.util.concurrent.CompletionService;

/**
 * Created by Yao on 2015/10/9.
 */
public class ReportRequest implements Runnable {

    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        service.submit(new ReportGenerator(name, "report"));
    }
}
