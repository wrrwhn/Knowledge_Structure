package syncBasic.syncCondition;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Yao on 2015/8/24.
 */
public class EventStorage {

    private int maxSize;
    private LinkedList<Date> dateList;

    public EventStorage() {
        maxSize= 5;
        dateList= new LinkedList<Date>();
    }

    public synchronized void set(){
        while (dateList.size()== maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        dateList.add(new Date());
        System.out.printf("Current DateList's count is %d\n", dateList.size());
        notifyAll();
    }

    public synchronized  void get(){
        while (dateList.size()== 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Date date= dateList.poll();
        System.out.printf("\tPoll the date[%s], the left count of datelist is %d\n", date.toString(), dateList.size());
        notifyAll();
    }
}
