package syncBasic.syncMethod;
/**
 * Created by Yao on 2015/8/20.
 */
public class Bank implements Runnable {

    private Acount acount;

    public Bank(Acount acount) {
        this.acount = acount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            acount.subBalance(1);
        }
    }
}
