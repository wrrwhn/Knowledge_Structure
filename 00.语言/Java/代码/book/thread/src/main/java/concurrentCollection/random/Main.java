package concurrentCollection.random;

/**
 * Created by Yao on 2016/3/8.
 */
public class Main {

    public static void main(String[] args) {

        final int SIZE= 5;

        Thread threads[] = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {

            threads[i]= new Thread(new Task());
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
