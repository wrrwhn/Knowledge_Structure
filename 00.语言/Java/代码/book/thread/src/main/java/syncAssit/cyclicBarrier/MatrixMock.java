package syncAssit.cyclicBarrier;

import java.util.Random;

/**
 * Created by Yao on 2015/9/24.
 */
public class MatrixMock {

    private int data[][];
    private int maxNum= 10;
    private int counter= 0;

    public MatrixMock(int size, int length, int num) {
        data= new int[size][length];
        Random random=new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j]= random.nextInt(maxNum);
                if(data[i][j]== num){
                    this.counter++;
                }
            }
        }

        System.out.printf("-->Thre are %d number(%d) in the generated data\n", this.counter, num);
    }

    public int[] getRow(int idx){
        return data[idx];
    }
}
