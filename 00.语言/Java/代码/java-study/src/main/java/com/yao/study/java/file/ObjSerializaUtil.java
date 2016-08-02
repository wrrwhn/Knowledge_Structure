package com.yao.study.java.file;

import com.yao.utils.custom.DateUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/16.
 */
public class ObjSerializaUtil {

    public static void main(String[] args) {
        ObjSerializaUtil.writeSeriaObj(new Object[]{new ClassLoading(), new ClassLoading()});
        List<Object> clList=  ObjSerializaUtil.readSeriaObj(DateUtils.getCurDay(), ClassLoading.class.getSimpleName());
        System.out.println(clList);
    }

    public static boolean writeSeriaObj(Object... objs) {
        Boolean result = false;
        String url = "D:\\tmp\\data\\smp\\%s\\%s.dat";   //TODO：转读配置
        Boolean bNewFile= false;

        if(null== objs || objs.length<= 0){
            return result;
        }

        try {
            //匹配至当前时间文件夹中
            String curDay = DateUtils.getCurDay();

            if (objs[0] instanceof ClassLoading) {
                url= String.format(url, curDay, ClassLoading.class.getSimpleName());
            } else {
                url= String.format(url, curDay, Object.class.getSimpleName());
            }
            System.out.println("ObjSerializaUtil.writeSeriaObj.url= "+ url);

            //文件不存在情况下创建相应文件夹和文件
            File file= new File(url);
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                bNewFile= true;
            }
            System.out.println("ObjSerializaUtil.writeSeriaObj.创建目录和文件");

            FileOutputStream fos = new FileOutputStream(url, true);
            ObjectOutputStream oos = bNewFile? new ObjectOutputStream(fos): new AppendOutputStream(fos);
            for(Object obj: objs) {
                oos.writeObject(obj);
            }

            oos.flush();
            oos.close();
            fos.close();
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Object> readSeriaObj(String dayStr, String className) {
        String url = "D:\\tmp\\data\\smp\\%s\\%s.dat";   //TODO：转读配置
        List<Object> list= new ArrayList<Object>();
        Object obj= null;

        try {
            url= String.format(url, dayStr, className);
            System.out.println("ObjSerializaUtil.readSeriaObj.url= "+ url);

            FileInputStream fis = new FileInputStream(url);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while(true) {
                try {
                    obj = ois.readObject();
                    list.add(obj);
                }catch (EOFException e){
                    break;  //由于无法定位目前对象文件中存在多少数量
                }
            }

            ois.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
