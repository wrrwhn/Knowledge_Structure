package basic.info;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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
        for(int i=1; i<= 10; i++){
            System.out.printf("%s: %d* %d= %d\n", Thread.currentThread().getName(), number, i, i* number);
        }
    }

    public static void main(String[] args) {

        int count= 10;
        PrintWriter pw;

        Thread threads[] = new Thread[count];
        Thread.State states[]= new Thread.State[count];

        for (int i = 0; i < count; i++) {
            threads[i]= new Thread(new Calculator(i));
            threads[i].setPriority(0== i%2? Thread.MAX_PRIORITY: Thread.MIN_PRIORITY);
            threads[i].setName("Thread_"+ i);
        }

        try{
            File file= new File(new File("").getAbsolutePath()+ "\\log.txt");
            System.out.println(file.getAbsolutePath());
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw=  new FileWriter(file);
            pw= new PrintWriter(fw);

            for (int i = 0; i < count; i++) {
                pw.printf("Main : Status of Thread"+ i+ " : "+ threads[i].getState()+ "\n");
                states[i]= threads[i].getState();
            }

            for (int i = 0; i < count; i++) {
                threads[i].start();
            }

            boolean finish= false;
            while (!finish){
                for (int i = 0; i < count; i++) {
                    if(states[i] != threads[i].getState()){
                        writeThreadInfo(pw, threads[i], states[i]);
                        states[i]= threads[i].getState();
                    }
                }
                finish= true;
                for (int i = 0; i < count; i++) {
                    finish= finish&& (Thread.State.TERMINATED== threads[i].getState());
                }
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    /***
     * 保存当前线程的变化情况和相关的线程信息
     *
     *  DI：线程的唯一标识符
     *  Name：线程名称
     *  Priority：线程的优先级，由 1 至 10 ， 其中 10 为最高，且不推荐改变
     *  Status：线程状态，可选状态为 NEW RUNNABLE BLOCKED WAITING TIME_WATING TERMINATED
     *
     * @param pw
     * @param thread
     * @param state
     */
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state){
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ***********************************************");
        pw.flush();
    }
}
