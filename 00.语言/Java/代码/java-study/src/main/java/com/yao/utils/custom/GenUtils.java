package com.yao.utils.custom;

import java.util.UUID;

/**
 * Created by Administrator on 2014/11/26.
 */
public class GenUtils {

    public static void main(String[] args){
        System.out.println(generateGUID());
    }

    /***
     * 自动生成GUID
     * @return
     */
    public static String generateGUID(){
        return UUID.randomUUID().toString();
    }
}
