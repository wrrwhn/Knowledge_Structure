package syncAssit.cyclicBarrier;

/**
 * Created by Yao on 2015/9/24.
 */
public class Grouper implements Runnable {

    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int data[] = results.getData();
        int cnt= 0;

        for (int i = 0; i < data.length; i++) {
            cnt+= data[i];
        }

        System.out.printf("<--Grouper total result is %d\n", cnt);
    }
}
