package concurrentCollection.priorityBlockList;

/**
 * Created by Yao on 2016/3/2.
 */
public class Event implements Comparable<Event> {

    private int priority;
    private int threadId;

    public Event() {
    }

    public Event(int priority, int threadId) {
        this.priority = priority;
        this.threadId = threadId;
    }

    public int getPriority() {
        return priority;
    }

    public int getThreadId() {
        return threadId;
    }

    @Override
    public int compareTo(Event o) {
        if (this.getPriority()* this.getThreadId() > o.getPriority()* o.getThreadId())
            return -1;
        else if (this.getPriority()* this.getThreadId() < o.getPriority()* o.getThreadId())
            return 1;
        else
            return 0;
    }
}
