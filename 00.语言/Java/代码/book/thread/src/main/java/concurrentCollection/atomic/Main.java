package concurrentCollection.atomic;

/**
 * Created by Yao on 2016/3/9.
 */
public class Main {

    public static void main(String[] args) {

        Account account = new Account();
        account.setBlance(10000);

        Thread company = new Thread(new Company(account));
        Thread bank = new Thread(new Bank(account));
        System.out.printf("init account= %d\n", account.getBlance());

        company.start();
        bank.start();
        try {
            company.join();
            bank.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("final account=%d\n", account.getBlance());
    }
}
