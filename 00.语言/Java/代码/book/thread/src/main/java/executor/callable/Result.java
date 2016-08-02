package executor.callable;

import java.util.concurrent.Future;

/**
 * Created by Yao on 2015/9/27.
 */
public class Result {

    private Integer num;
    private Future<Integer> res;

    public Result(Integer num, Future<Integer> res) {
        this.res = res;
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Future<Integer> getRes() {
        return res;
    }

    public void setRes(Future<Integer> res) {
        this.res = res;
    }
}
