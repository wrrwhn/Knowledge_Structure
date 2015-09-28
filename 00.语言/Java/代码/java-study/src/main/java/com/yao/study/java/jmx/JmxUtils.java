package com.yao.study.java.jmx;

import sun.management.ConnectorAddressLink;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerFactory;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/11/6.
 */
public class JmxUtils {

    public static void main(String[] args)throws Exception{
        //3568  tomcat
        //7204  mule
//        turnPID2RMI(7204);
        qeuryProcess();
    }

    /***
     * 测试本地JMX的读取和相关的内容权限
     * @throws Exception
     */
    public static void testLocalRMI() throws Exception{
        MBeanServer server = MBeanServerFactory.createMBeanServer();
        String[] domains= server.getDomains();
        System.out.println("Domains:");
        for(String domain: domains){
            System.out.println("\t"+ domain);
        }

        MBeanServerConnection connection= JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi"), null).getMBeanServerConnection();
        domains= connection.getDomains();
        System.out.println("Domains:");
        for(String domain: domains){
            System.out.print(","+ domain);
        }
    }

    /***
     * 获取当前服务内的tomcat/mule相关的进程
     */
    public static void queryProcessTest(){
        BufferedReader reader= null;
        try {
            Process proc=
                    Runtime.getRuntime().exec("tasklist ");
//            Runtime.getRuntime().exec("powershell get-process java ^| format-list id,name,path");
//            Runtime.getRuntime().exec("wmic path win32_process get processid,caption,commandline| findstr java.exe");

            reader= new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line= null;
            while((line= reader.readLine())!= null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     *  获取windows/Linux环境下的tomcat及mule项目PID
     */
    public static void qeuryProcess(){
        List<JpsVo> jpsList= new ArrayList<JpsVo>();
        String jps = "jps";
        try {
            if(System.getProperty("os.name").indexOf("Windows")> 1) {
               jps= System.getenv("JAVA_HOME") + File.separator + "bin" + File.separator + "jps.exe";   //windows环境自动获取java环境目录
            }

            Process p = Runtime.getRuntime().exec(jps);
            String line= null;
            String[] param= null;
            BufferedReader reader= new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((line= reader.readLine())!= null){
                param= line.split(" ");
                //目前只针对Tomcat环境进行过滤 TODO：可于数据库配置并使用逗号隔开
                if(param[1].equals("Bootstrap") || param[1].equals("MuleContainerBootstrap"))
                    jpsList.add(new JpsVo(Integer.parseInt(param[0]), param[1], turnPID2RMIStr(Integer.parseInt(param[0]))));
            }

            for(JpsVo vo: jpsList){
                System.out.println(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取指定进程的相关对象信息
     * @param pid   本地进程ID，此处特指TOMCAT/MULE项目
     */
    public static void QueryPidDomain(int pid) throws Exception{
        String address = ConnectorAddressLink.importFrom(pid);
        JMXServiceURL jmxUrl = new JMXServiceURL(address);
        MBeanServerConnection mbsc= JMXConnectorFactory.connect(jmxUrl).getMBeanServerConnection();
        String[] domains= mbsc.getDomains();
        for(String domain: domains){
            System.out.println("\t"+ domain);
        }
    }

    /***
     * 将PID自动转换为本地的RMI地址格式
     * @param pid   本地进程ID，此处特指TOMCAT/MULE项目
     */
    public static String turnPID2RMIStr(int pid) throws Exception{
        return ConnectorAddressLink.importFrom(pid);
    }
}
