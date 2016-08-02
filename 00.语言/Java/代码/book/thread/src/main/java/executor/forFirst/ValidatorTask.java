package executor.forFirst;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  ThreadPoolExecutor.invokeAny(Collection<? extends Callable<T>> tasks)：返回第一个处理完成的结果
 *      All return -> first return
 *      All error -> throw ExecutionException
 *      First Return & left error -> first return
 *      some error some return -> first return
 *
 */
public class ValidatorTask implements Callable<String> {

    private UserValidator validator;
    private String userName;
    private String passWord;

    public ValidatorTask(UserValidator validator, String userName, String passWord) {
        this.validator = validator;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String call() throws Exception {

        if(!validator.validator(userName, passWord)){
            System.out.printf("->ValidatorTask: %s has not be found\n", validator.getName());
            throw new Exception("");
        }else{
            System.out.printf("->ValidatorTask: %s has been found\n", validator.getName());
            return validator.getName();
        }
    }


    public static void main(String[] args) {
        String userName= "yqj";
        String passWord= "dcyy";

        UserValidator vRedis= new UserValidator("Redis");
        UserValidator vTomcat = new UserValidator("Tomcat");

        ValidatorTask vtRedis= new ValidatorTask(vRedis, userName, passWord);
        ValidatorTask vtTomcat = new ValidatorTask(vTomcat, userName, passWord);

        List<ValidatorTask> list = new ArrayList<>();
        list.add(vtRedis);
        list.add(vtTomcat);

        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        try {
            System.out.println("<-ValidatorTask: Result is " + executor.invokeAny(list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
