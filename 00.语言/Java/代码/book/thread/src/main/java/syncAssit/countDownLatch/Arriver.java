package syncAssit.countDownLatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/23.
 */
public class Arriver implements Runnable {

    private MeetingCtr meetingCtr;
    private String personName;

    public Arriver(MeetingCtr meetingCtr, String personName) {
        this.meetingCtr = meetingCtr;
        this.personName = personName;
    }

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep((long)(Math.random()* 10));
            this.meetingCtr.arrive(this.personName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
