package basic.create;

/**
 * Created by Yao on 2015/7/27.
 */
public class Calculator implements Runnable {

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i=1; i<= 10; i++){
            System.out.printf("%s: %d* %d= %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
    }

    /***
     * 当所有非守护进程执行完成时 或 调用 System.exit() 方法，程序执行完成
     * 仅当 Start 方法执行后，系统才会创建方法
     * @param args
     */
    public static void main(String[] args) {
        for (int i=1; i<= 10; i++){
            Calculator calculator= new Calculator(i);
            new Thread(calculator).start();
        }
    }
}
