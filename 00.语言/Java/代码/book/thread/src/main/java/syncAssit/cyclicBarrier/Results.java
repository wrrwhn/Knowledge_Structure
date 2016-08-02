package syncAssit.cyclicBarrier;

/**
 * Created by Yao on 2015/9/24.
 */
public class Results {

    public int data[];

    public Results(int size) {
        this.data = new int[size];
    }

    public void setData(int idx, int val){
        data[idx]= val;
    }

    public int[] getData(){
        return data;
    }
}
