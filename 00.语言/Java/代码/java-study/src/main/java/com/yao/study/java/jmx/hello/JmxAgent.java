package com.yao.study.java.reflection.jmx.hello;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by Administrator on 2014/11/6.
 */
public class JmxAgent {

    public static void main(String[] args) throws Exception{
        MBeanServer mbs= ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("jmx.hello:type=Hello");
        Hello hello = new Hello();
        mbs.registerMBean(hello, name);
        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
