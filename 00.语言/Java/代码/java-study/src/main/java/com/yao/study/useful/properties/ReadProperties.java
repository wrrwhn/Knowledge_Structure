package com.yao.study.useful.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2014/11/12.
 */
public class ReadProperties {

    private final static String fileName= "server.properties";

    public static void main(String[] args){
        printProperties(fileName);
    }

    /***
     * 使用java自带方法进行文件信息读取
     * @param fileName
     */
    public static void printProperties(String fileName){
        Properties prop= new Properties();
        InputStream input = null;

        try {
            input= ReadProperties.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(input);
            System.out.println(prop.getProperty("appId"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != input){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
