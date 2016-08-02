package basic.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 *  工厂设计模式实现
 *
 *  优点：
 *      1.更容易修改创建模式
 *      2.容易有限约束创建个数
 *      3.容易为统计创建等情况信息
 *      4.对生成线程进行校验
 */
public class TaskFactory implements ThreadFactory {

    int counter;
    String prefix;
    List<String> states;

    public TaskFactory() {
        this.counter = 0;
        this.prefix = "TaskFactory";
        this.states = new ArrayList<String>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread= new Thread(r, prefix+ "_Thread-"+ counter);
        counter++;
        states.add(String.format("Create Thread %s in name %s on %s", thread.getId(), thread.getName(), new Date()));
        return thread;
    }

    public String getStates(){
        StringBuffer buffer= new StringBuffer();
        Iterator<String> iStates= states.iterator();
        while (iStates.hasNext()){
            buffer.append(String.format("\t%s\n", iStates.next()));
        }
        return buffer.toString();
    }
}
