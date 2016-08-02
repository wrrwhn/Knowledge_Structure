package executor.cancel;

import java.util.concurrent.Callable;

/**
 * Created by Yao on 2015/9/30.
 */
public class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        boolean bState= true;
        while(bState){
            System.out.printf("\tTask doing\n");
            Thread.sleep(1000);
//            bState= false;
        }

        return "hello";
    }
}
