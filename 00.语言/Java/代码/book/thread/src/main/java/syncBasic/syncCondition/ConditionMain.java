package syncBasic.syncCondition;

/**
 * Created by Yao on 2015/8/24.
 */
public class ConditionMain {

    public static void main(String[] args) {

        EventStorage storage= new EventStorage();
        Thread productor= new Thread(new Productor(storage));
        Thread comsumer = new Thread(new Consumer(storage));

        productor.start();
        comsumer.start();
    }
}
