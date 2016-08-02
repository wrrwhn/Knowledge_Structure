package custom.threadFactory;

import java.util.Date;

/**
 * Created by Yao on 2016/3/13.
 */
public class CustomThread extends Thread {

    private Date createTime;
    private Date startTime;
    private Date endTime;

    public CustomThread(Runnable target, String name) {
        super(target, name);
        setCreateTime();
    }

    @Override
    public synchronized void start() {
        setStartTime();
        super.start();
        setEndTime();

        System.out.println(this.toString());
    }


    @Override
    public String toString() {
        return String.format("CustomThread[%s] run for %d", getName(), getRunTime());
    }

    private void setCreateTime(){
        this.createTime= new Date();
    }

    private void setStartTime(){
        this.startTime= new Date();
    }

    private void setEndTime(){
        this.endTime= new Date();
    }

    private long getRunTime(){
        return this.endTime.getTime()- this.startTime.getTime();
    }
}
