package syncAssit.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Yao on 2015/9/23.
 */
public class MeetingCtr implements Runnable {

    private CountDownLatch countDownLatch;

    public MeetingCtr(int cnt) {
        this.countDownLatch= new CountDownLatch(cnt);
    }

    public void arrive(String personName){
        System.out.printf("-> %s is arrive the meeting, current count is %d\n", personName, this.countDownLatch.getCount());
        this.countDownLatch.countDown();
    }

    @Override
    public void run() {
        try{
            this.countDownLatch.await();
            System.out.println("----> All person is arrived");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
