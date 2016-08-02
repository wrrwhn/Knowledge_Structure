package concurrentCollection.delay;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2016/3/4.
 */
public class Event implements Delayed {

    private Date startTime;

    public Event(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {

        Date now = new Date();
        long delay = startTime.getTime() - now.getTime();

        return unit.convert(delay, TimeUnit.MICROSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long delay = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if (delay < 0)
            return -1;
        else if (delay > 0)
            return 1;
        else
            return 0;
    }
}
