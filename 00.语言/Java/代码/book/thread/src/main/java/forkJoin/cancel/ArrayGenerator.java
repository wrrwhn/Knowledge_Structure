package forkJoin.cancel;

import java.util.Random;

/**
 * Created by Yao on 2015/12/10.
 */
public class ArrayGenerator {

    public static int[] GenerateArray(int size){
        int array[] = new int[size];
        Random random= new Random();
        for (int i = 0; i < size; i++) {
            array[i]= random.nextInt(10);
        }
        return array;
    }
}
