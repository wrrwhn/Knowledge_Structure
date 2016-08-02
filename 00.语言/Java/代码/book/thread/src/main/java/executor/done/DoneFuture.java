package executor.done;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Yao on 2015/9/30.
 */
public class DoneFuture extends FutureTask<String> {

    private String name;

    public DoneFuture(Callable<String> callable) {
        super(callable);
        this.name= ((ExecTask)callable).getName();
    }

    @Override
    protected void done() {
        if(isCancelled())
            System.out.printf("\t%s has been cancelled\n", this.name);
        else
            System.out.printf("\t%s has done\n", name);
    }
}
