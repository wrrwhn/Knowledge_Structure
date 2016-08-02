package concurrentCollection.skipList;


import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Yao on 2016/3/8.
 */
public class Main {

    public static void main(String[] args) {

        final int SIZE = 25;

        ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
        Thread threads[] = new Thread[SIZE];

        char start = 'A';
        for (int i = 0; i < SIZE; i++) {
            threads[i] = new Thread(new Task(String.valueOf((char)(start + i)), map));
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: finish the data\n");

        Map.Entry<String, Contact> element;
        element = map.firstEntry();
        System.out.printf("\tMain: First Entry: %s: %s\n", element.getKey(), element.getValue().toString());
        element = map.lastEntry();
        System.out.printf("\tMain: First Entry: %s: %s\n", element.getKey(), element.getValue().toString());

        ConcurrentNavigableMap<String, Contact> conMap = map.subMap("B8", "D2");
        do {
            element = conMap.pollFirstEntry();
            if (null != element)
                System.out.printf("\tMain: list.Entry is %s: %s\n", element.getKey(), element.getValue().toString());
        } while (null != element);
        System.out.printf("Main: finish the read\n");

    }
}
