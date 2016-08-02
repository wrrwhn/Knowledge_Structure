package encapsulation.use;

import java.util.Arrays;

/**
 * Created by Yao on 2015/4/6.
 */
public class DuckSortDrive {

    public static void main(String[] args){

        Duck[] ducks= {
                new Duck("A", 8),
                new Duck("B", 5),
                new Duck("C", 1),
                new Duck("D", 0),
                new Duck("E", 7),
                new Duck("F", 7),
                new Duck("G", 7),
                new Duck("H", 5),
        };

        System.out.println("============================\nBefore Sort\n============================");
        display(ducks);
        System.out.println("============================\nAfter Sort\n============================");
        Arrays.sort(ducks);
        display(ducks);
    }

    public static void display(Duck[] ducks){
        for(int i=0; i< ducks.length; i++){
            System.out.println(ducks[i]);
        }
    }
}
