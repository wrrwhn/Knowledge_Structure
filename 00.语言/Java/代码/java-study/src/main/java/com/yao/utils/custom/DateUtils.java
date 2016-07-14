package com.yao.utils.custom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/10/29.
 */
public class DateUtils {

    //时间输出格式
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";

    public static void main(String[] args) {

        compareDate();
    }

    /**
     * 自行输入格式
     *
     * @param forStr
     * @return
     */
    public static String formatCurTime(String forStr) {
        SimpleDateFormat format = new SimpleDateFormat(forStr);
        return format.format(new Date());
    }

    /**
     * 获取当前时间，精确至毫秒
     *
     * @return
     */
    public static String getCurTime() {
        return formatCurTime(YYYY_MM_DD_HH_MM_SS_SSS);
    }

    /**
     * 返回当前日期
     *
     * @return
     */
    public static String getCurDay() {
        return formatCurTime(YYYYMMDD);
    }

    /**
     * 获取时间区间内的日期列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private List<Date> listDateInterval(Date startDate, Date endDate) {

        List<Date> dateList = new ArrayList<>();
        if (null == startDate || null == endDate || startDate.getTime() > endDate.getTime())
            return dateList;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().getTime() <= endDate.getTime()) {
            dateList.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }

        return dateList;
    }

    /**
     * 获取两个时间集合的差集
     *
     * @param originStart
     * @param originEnd
     * @param start
     * @param end
     * @return
     */
    private List<Date> listDiffDateInterval(Date originStart, Date originEnd, Date start, Date end) {

        List<Date> dateList = new ArrayList<>();
        if (null == originStart || null == originEnd || null == start || null == end ||
                originEnd.getTime() < originStart.getTime() ||
                end.getTime() < start.getTime() ||
                (originStart.getTime() <= start.getTime() && originEnd.getTime() >= end.getTime()))
            return dateList;

        if (end.getTime() <= originStart.getTime() || start.getTime() >= originEnd.getTime()) {
            dateList = listDateInterval(start, end);
            if (end.getTime() == originStart.getTime())
                dateList.remove(dateList.size() - 1);
            if (start.getTime() == originEnd.getTime())
                dateList.remove(0);
        } else if (start.getTime() < originStart.getTime() && end.getTime() > originEnd.getTime()) {
            dateList = listDateInterval(start, originStart);
            dateList.remove(dateList.size() - 1);
            List<Date> tmp = listDateInterval(originEnd, end);
            tmp.remove(0);
            dateList.addAll(tmp);
        } else {
            dateList = listDateInterval(start.getTime() < originStart.getTime() ? start : originEnd, end.getTime() > originEnd.getTime() ? end : originStart);
            if (originEnd.getTime() == end.getTime())
                dateList.remove(dateList.size() - 1);
            if (originStart.getTime() == start.getTime())
                dateList.remove(0);
        }

        return dateList;
    }

    private static void compareDate() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 7, 10, 0, 0, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        startDate.add(Calendar.DAY_OF_YEAR, 2);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 7, 12, 0, 0, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_SSS);
        System.out.println(format.format(startDate.getTime()));
        System.out.println(format.format(endDate.getTime()));
        System.out.println(endDate.compareTo(startDate));
    }
}
