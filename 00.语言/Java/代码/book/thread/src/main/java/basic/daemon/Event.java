package basic.daemon;

import java.util.Date;

/**
 * Created by Yao on 2015/8/4.
 */
public class Event {

    private Date date;
    private String event;

    public Event(String event) {
        this.date= new Date();
        this.event= event;
    }

    public Event(Date date, String event) {
        this.date = date;
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "hashcode=" + this.hashCode()+
                ", event='" + event + '\'' +
                '}';
    }
}
