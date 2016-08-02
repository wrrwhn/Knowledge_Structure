package custom.threadFactory;

/**
 * Created by Yao on 2016/3/13.
 */
public class Main {

    public static void main(String[] args) {

        CustomFactory factory= new CustomFactory("Hello");

        Thread thread = factory.newThread(new CustomTask());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
