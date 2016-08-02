package yao.study.java.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;

/**
 *  文件于指定时间范围内创建的将被筛选出来
 *
 *  前提：
 *      文件名称为请求当前时间下的YYYYMMDD...形式存储
 *
 * Created by Administrator on 2015/2/15.
 */
public class FileFilterByDate implements FilenameFilter {

    public static void main(String[] args){
        File folder = new File("D:\\temp\\sync\\1\\handler");
        Calendar start= Calendar.getInstance();
        start.set(2015, 2, 5, 0, 0);
        Calendar end= Calendar.getInstance();
        end.set(2015, 2, 16, 18, 30);
        String[] fileNames= folder.list(new FileFilterByDate(start, end));
        for(String str: fileNames){
            System.out.println(str);
        }
    }

    private Calendar startTime;
    private Calendar endTime;


    public FileFilterByDate() {
    }

    public FileFilterByDate(Calendar startTime, Calendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean accept(File dir, String name) {
        Calendar now = Calendar.getInstance();
        now.set(
                Integer.valueOf(name.substring(0, 4)),
                Integer.valueOf(name.substring(4, 6)),
                Integer.valueOf(name.substring(6, 8)),
                Integer.valueOf(name.substring(8, 10)),
                Integer.valueOf(name.substring(10, 12))
        );

        return now.after(startTime) && now.before(endTime);
    }
}
