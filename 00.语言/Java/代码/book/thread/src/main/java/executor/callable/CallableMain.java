package executor.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 *  Callable
 *      作用：
 *          支持带返回值的线程
 *      方法：
 *          call()：启动服务并返回计算结果
 *          Future.get()：获取最后的计算结果
 *          Future.get(long timeout, TimeUnit unit)：获取最后的计算结果，若等待时间超出限定的 timeout ，则将返回 null
 *          Future.cancel()：在未取消和未结束的情况下，进行当前任务的取消
 */
public class CallableMain {

    public static void main(String[] args) {

        int callCnt= 10;
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Result> list = new ArrayList<Result>();
        Random random = new Random();

        for (int i = 0; i < callCnt; i++) {
            Integer num = random.nextInt(10);
            FactorialCalc calc= new FactorialCalc(num);
            Future<Integer>  future= executor.submit(calc);
            list.add(new Result(num, future));
        }

        do{
            System.out.printf("CallableMain: completed count is %d, and the first one's status is %s\n", executor.getCompletedTaskCount(), list.get(0).getRes().isDone());
            try {
                TimeUnit.MILLISECONDS.sleep(callCnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (executor.getCompletedTaskCount()< list.size());

        System.out.printf("CallableMain: Results\n");
        for (int i = 0; i < list.size(); i++) {
            try{
                Result res = list.get(i);
                System.out.printf("\t->%d!= %d\n", res.getNum(), res.getRes().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // !!!!!!!!!!非常容易忘记啊
        executor.shutdown();
    }
}
