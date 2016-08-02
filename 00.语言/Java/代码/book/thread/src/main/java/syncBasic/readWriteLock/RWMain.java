package syncBasic.readWriteLock;

/**
 *  ReadWriteLock， 实现类为 ReentrantReadWriteLock， 主要实现了读写时的不同锁控制
 *      注：写的时候可读，读的时候不可进行写操作
 */
public class RWMain {

    public static void main(String[] args) {
        Price price= new Price();

        for (int i = 0; i < 5; i++) {
            new Thread(new Reader(price)).start();
        }
        new Thread(new Writer(price)).start();
    }
}
