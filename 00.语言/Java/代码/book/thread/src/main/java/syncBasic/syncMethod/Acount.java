package syncBasic.syncMethod;

/**
 * Created by Yao on 2015/8/20.
 */
public class Acount {
    private Double balance;

    public Acount(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public synchronized void addBalance(double amount){
        double tmp = balance;

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp+= amount;
        this.balance= tmp;
    }

    public synchronized void subBalance(double amount){
        double tmp = balance;

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp-= amount;
        this.balance= tmp;
    }
}
