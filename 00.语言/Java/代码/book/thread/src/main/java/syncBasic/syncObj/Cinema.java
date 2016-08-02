package syncBasic.syncObj;

/**
 * Created by Yao on 2015/8/20.
 */
public class Cinema {

    private Object keyForA;
    private Object keyForB;
    private int countForA;
    private int countForB;

    public Cinema() {
        this.keyForA = new Object();
        this.keyForB = new Object();
        this.countForA = 20;
        this.countForB = 20;
    }

    public boolean sellTicket4A(int cnt){
        synchronized (keyForA){
            if(countForA> cnt){
                countForA-= cnt;
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean returnTicket4A(int cnt){
        synchronized (keyForA){
            countForA+= cnt;
            return true;
        }
    }

    public boolean sellTicket4B(int cnt){
        synchronized (keyForB){
            if(countForB> cnt){
                countForB-= cnt;
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean returnTicket4B(int cnt){
        synchronized (keyForB){
            countForB+= cnt;
            return true;
        }
    }

    public int getCountForB() {
        return countForB;
    }

    public int getCountForA() {
        return countForA;
    }
}
