package executor.forFirst;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/27.
 */
public class UserValidator {

    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public boolean validator(String userName, String passWord){
        try {
            long timeout= (long) (Math.random()* 100);
            TimeUnit.MILLISECONDS.sleep(timeout);
            System.out.printf("\tUserValidator: validating a user during %d ms\n", timeout);
        } catch (InterruptedException e) {
            return false;
        }
//        return new Random().nextBoolean();
        return true;
    }

    public String getName() {
        return name;
    }
}
