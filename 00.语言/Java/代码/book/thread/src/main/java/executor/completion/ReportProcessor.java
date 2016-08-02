package executor.completion;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/10/9.
 */
public class ReportProcessor implements Runnable {

    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.end= false;
        this.service = service;
    }

    @Override
    public void run() {

        System.out.println("\t\tReportProcessor start");

        while (!end){
            try {
                Future<String> result= service.poll(20, TimeUnit.SECONDS);
                if(null!= result){
                    String report = result.get();
                    System.out.printf("\t\tReportProcessor received %s\n", report);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\t\tReportProcessor finish");
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
