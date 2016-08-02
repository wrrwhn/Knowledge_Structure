package syncBasic.syncMethod;

/**
 *  使用 synchronized 关键字声明的对象或方法，只有一个执行线程允许访问，有其它线程试图使用，会被挂起直到执行完成后再唤醒
 *      使用 synchronized 声明的方法都是临界区，同一时间只允许一个访问
 *
 *  注：使用 synchronized 关键字会降低性能，建议减小临界区的范围
 *      可以使用 synchronized(this) 来引用正在执行方法的对象
 */
public class Main {

    public static void main(String[] args) {
        Acount acount= new Acount(100D);
        Bank bankTask= new Bank(acount);
        Thread bank= new Thread(bankTask);
        Company companyTask= new Company(acount);
        Thread company= new Thread(companyTask);

        System.out.printf("Initial Balance: %f\n", acount.getBalance());

        company.start();
        bank.start();
        try {
            company.join();
            bank.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("End Balance: %f\n", acount.getBalance());
    }
}
