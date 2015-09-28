package com.yao.utils.custom;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2014/10/29.
 */
public class DateUtils {

    //时间输出格式
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD= "yyyyMMdd";


    public static void main(String[] args){
        System.out.println(getCurDay());
    }


    /***
     * 自行输入格式
     * @param forStr
     * @return
     */
    public static String formatCurTime(String forStr){
        SimpleDateFormat format= new SimpleDateFormat(forStr);
        return format.format(new Date());
    }

    /***
     * 获取当前时间，精确至毫秒
     * @return
     */
    public static String getCurTime(){
        return formatCurTime(YYYY_MM_DD_HH_MM_SS_SSS);
    }

    /***
     * 返回当前日期
     * @return
     */
    public static String getCurDay(){
        return formatCurTime(YYYYMMDD);
    }
}
