package com.yao.study.java.reflection.lock;

import com.yao.utils.custom.DateUtils;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2014/11/20.
 */
public class ReentrantLockImpl implements Runnable{
    private static int money = 0;
    private int type= 0;
    private ReentrantLock lock= new ReentrantLock() ;


    public static void main(String[] args){
        Random random= new Random();
        for(int i= 0; i<= 100; i++){
            new Thread(new ReentrantLockImpl(random.nextInt(2))).start();
        }
    }


    public ReentrantLockImpl(int type) {
        this.type = type;
    }

    public void incr(){
        lock.lock();
        try {
            ++money;
            System.out.println(DateUtils.getCurTime()+ "\tincr:"+ money);
        }finally {
            lock.unlock();
        }
    }

    public void read(){
        lock.lock();
        try{
            System.out.println(DateUtils.getCurTime()+ "\tread:"+ money);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        switch (type){
            case 0:
                read();
                break;
            default:
                incr();
                break;
        }
    }
}
