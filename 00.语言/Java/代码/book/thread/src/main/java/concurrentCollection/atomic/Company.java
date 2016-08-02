package concurrentCollection.atomic;

/**
 * Created by Yao on 2016/3/9.
 */
public class Company implements Runnable {

    private Account account;

    public Company(Account account) {
        this.account = account;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.account.add(100);
        }
    }
}
