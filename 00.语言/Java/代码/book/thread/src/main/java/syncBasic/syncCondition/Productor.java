package syncBasic.syncCondition;

/**
 * Created by Yao on 2015/8/24.
 */
public class Productor implements Runnable {

    private EventStorage storage;

    public Productor(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
