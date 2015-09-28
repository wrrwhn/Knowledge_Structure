package com.yao.study.java.reflection.jmx.hello;

/**
 * Created by Administrator on 2014/11/6.
 */
public class Hello implements HelloMBean { 		//todo实现
    private final String name = "Reginald";
    private int cacheSize = DEFAULT_CACHE_SIZE;
    private static final int DEFAULT_CACHE_SIZE = 200;

    public String getName() {
        return this.name;
    }
    public int getCacheSize() {
        return this.cacheSize;
    }
    public synchronized void setCacheSize(int size) {
        this.cacheSize = size;
        System.out.println("Cache size now " + this.cacheSize);
    }
}
