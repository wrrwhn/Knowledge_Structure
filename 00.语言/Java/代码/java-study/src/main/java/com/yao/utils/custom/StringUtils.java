package com.yao.utils.custom;

/**
 * Created by Administrator on 2014/11/14.
 */
public class StringUtils {

    public static void main(String[] args){
        System.out.println(format("bus:%d:basic.info:hash", 779));
        System.out.println(format("bus:%s:%d:basic.info:hash", "sso", 779));
    }

    /***
     * 判断文本是否为空
     *      1.输入为null
     *      2.输入移除空格后长度为0
     * @param input
     * @return
     */
    public static boolean isEmpty(String input){
        boolean result= false;
        if(null== input || input.trim().isEmpty()){
            result= true;
        }
        return result;
    }

    /***
     * 快速定位输入字符串通配符并对位进行替换
     * @param key   字符串输入
     * @param valus 替换的相关对象
     * @return  替换完成后的结构
     */
    public static String format(String key, Object... valus){
        return String.format(key, valus);
    }
}
