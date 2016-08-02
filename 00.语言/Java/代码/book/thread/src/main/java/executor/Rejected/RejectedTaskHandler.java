package executor.Rejected;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Yao on 2015/10/22.
 */
public class RejectedTaskHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("\tRejectedTaskHandler.rejectedExecution" +
                "{\n\t\t'Runner': '%s',\n" +
                "\t\t'executor': '%s', \n" +
                "\t\t'executor.isTerminating': '%s', \n" +
                "\t\t'executor.isTerminated': '%s', \n" +
                "\t\t'executor.isShutdown': '%s' }\n",
            r.toString(), executor.toString(), executor.isTerminating(), executor.isTerminated(), executor.isShutdown());
    }
}
