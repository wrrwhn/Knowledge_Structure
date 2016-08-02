package basic.Interrupt;

/**
 * Created by Yao on 2015/7/28.
 */
public class SimpleInterrupt implements Runnable {

    @Override
    public void run() {
        long number= 1L;
        while (true){
            if(IsPrime(number)){
                System.out.println(number+ " is prime");
            }
            if(IsInterrupt()){
                System.out.println("Current Thread is interrupt!");
                return;
            }
            number++;
        }
    }

    /***
     * 校验当前进程是否中断
     * @return
     */
    private boolean IsInterrupt() {
        return Thread.currentThread().isInterrupted();
    }

    private boolean IsPrime(long number) {
        if(number<= 2){
            return true ;
        }
        for (int i = 2; i < number; i++) {
            if(0== number%i){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task= new Thread(new SimpleInterrupt());
        task.start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        /// 手动设置当前进程中断
        task.interrupt();
    }
}
