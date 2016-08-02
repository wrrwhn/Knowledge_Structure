package custom.threadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/9.
 */
public class Main {

    public static void main(String[] args) {

        final int NUM = 10;

        CustomExecutor executor = new CustomExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < NUM; i++) {
            Future<String> result = executor.submit(new SleepTwoSecondsTask());
            results.add(result);
        }

        for (int i = 0; i < NUM / 2; i++) {
            try {
                System.out.printf("\tResult[%d]= %s\n", i, results.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        for (int i = NUM / 2; i < NUM; i++) {
            try {
                System.out.printf("\tResult[%d]= %s\n", i, results.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main finished\n");
    }
}
