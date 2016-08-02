package basic.daemon;

import java.util.Date;
import java.util.Deque;

/**
 * Created by Yao on 2015/8/4.
 */
public class CleanerTask extends Thread {

    private Deque<Event> events;

    public CleanerTask(Deque<Event> events) {
        this.events = events;
        setDaemon(true);    // 设置该服务为守护进程，即当主进程其它事务完成后，不会因为当前服务未完成而影响主服务完结
    }

    @Override
    public void run() {
        while (true) {
            clean();
        }
    }

    public void clean(){

        long dif= 0;

        if (0 == events.size()) {
            return;
        }

        do {
            if (0 == events.size()) {
                return;
            }
            Event e = events.getLast();
            dif = new Date().getTime() - e.getDate().getTime();
            if (dif > 2000) {
                events.removeLast();
                System.out.println(String.format("Clean from  %s, Left Count= %s, List=> %s", e.getEvent(), events.size(), events.toString()));
            }
        } while (dif > 2000);
    }
}
