package concurrentCollection.atomic;

/**
 * Created by Yao on 2016/3/9.
 */
public class Bank implements Runnable {

    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.account.substract(111);
        }
    }
}
