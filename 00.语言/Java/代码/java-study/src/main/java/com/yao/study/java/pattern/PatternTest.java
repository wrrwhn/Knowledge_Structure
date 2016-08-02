package com.yao.study.java.pattern;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {

        String path = "d:\\desk\\PPT\\ppt2h5\\f88319dd-877e-48c9-9c9a-b271fe4960b9.pptx\\res\\image";
        File file = new File(path);
        if (file.exists()) {
            System.out.printf("Pattern.start at %s\n", new Date());
            for (File f : file.listFiles()) {
                if (Pattern.matches("slide-rId\\d+.jpg", f.getName())) {
                    System.out.println("\t" + f.getName());
                }
            }
            System.out.printf("Pattern.finish at %s\n", new Date());

            System.out.printf("Filter.start at %s\n", new Date());
            String[] names = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return Pattern.matches("slide-rId\\d+.jpg", name);
                }
            });
            for (String name : names) {
                System.out.println("\t" + name);
            }
            System.out.printf("Filter.finish at %s\n", new Date());

            System.out.printf("FileName-Pattern.start at %s\n", new Date());
            List<Integer> sIdLst= new ArrayList<>();
            names= file.list();
            Pattern pattern= Pattern.compile("slide-rId(\\d+).jpg");
            for(String name : names){
                Matcher matcher= pattern.matcher(name);
                if(matcher.find()){
                    try{
                        sIdLst.add(Integer.parseInt(matcher.group(1)));
                    }catch (Exception e){}
                }
            }
            Collections.sort(sIdLst);
            System.out.println(sIdLst);
            System.out.printf("FileName-Pattern.finish at %s\n", new Date());
        }
    }
}
