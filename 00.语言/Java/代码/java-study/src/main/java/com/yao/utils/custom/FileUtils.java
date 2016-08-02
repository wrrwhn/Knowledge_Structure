package com.yao.utils.custom;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileUtils {

    public static void main(String[] args) {
        List<Integer> sIdxLst = new ArrayList<>();
        Pattern pattern = Pattern.compile("slide-rId(\\d+).jpg");
        String[] fileNames = {"slide-rId9.jpg", "slide-rId10.jpg", "slide-rId11.jpg", "slide-rId12.jpg", "slide-rId13.jpg", "slide-rId14.jpg", "slide-rId15.jpg", "slide-rId16.jpg", "slide-rId17.jpg", "slide-rId18.jpg", "slide-rId19.jpg", "slide-rId20.jpg", "slide-rId21.jpg", "slide-rId22.jpg", "slide-rId23.jpg", "slide-rId24.jpg", "slide-rId25.jpg", "slide-rId26.jpg", "slide-rId27.jpg", "slide-rId28.jpg", "slide-rId29.jpg", "slide-rId2.jpg", "slide-rId30.jpg", "slide-rId31.jpg", "slide-rId32.jpg", "slide-rId3.jpg", "slide-rId4.jpg", "slide-rId5.jpg", "slide-rId6.jpg", "slide-rId7.jpg", "slide-rId8.jpg"};
        System.out.println("\nfileNames");
        for (String filename : fileNames)
            System.out.print(filename + " ");

        List<String> fileNameList = Arrays.asList(fileNames);
        fileNameList = fileNameList.stream().filter(p -> pattern.matcher(p).matches()).collect(Collectors.toList());
        Collections.sort(fileNameList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int idx1 = 0;
                int idx2 = 0;
                Matcher matcher = pattern.matcher(o1);
                if (matcher.find()) {
                    idx1 = Integer.parseInt(matcher.group(1));
                }
                matcher = pattern.matcher(o2);
                if (matcher.find()) {
                    idx2 = Integer.parseInt(matcher.group(1));
                }
                return idx1 - idx2;
            }
        });
        System.out.println("\nfileNameList");
        for (String filename : fileNameList)
            System.out.println(filename + " ");

        // replace file
        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("slide-rId17.jpg", "slide-rId28.jpg");
        replaceMap.put("slide-rId24.jpg", "slide-rId28.jpg");
        replaceMap.put("slide-rId9.jpg", "slide-rId28.jpg");
        if (null != replaceMap && replaceMap.size() > 0) {
            int size = fileNameList.size();
            for (int i = 0; i < size; i++) {
                String fileName = fileNameList.get(i);
                if (replaceMap.keySet().contains(fileName))
                    fileNameList.set(i, replaceMap.get(fileName));
            }
        }
        System.out.println("\nfileNameList");
        for (String filename : fileNameList)
            System.out.println(filename + " ");

        for (String fileName : fileNameList) {
            Matcher matcher = pattern.matcher(fileName);
            if (matcher.find()) {
                try {
                    int idx = Integer.parseInt(matcher.group(1));
                    sIdxLst.add(idx);
                } catch (NumberFormatException nfe) {
                    System.out.println("fuck");
                } catch (IndexOutOfBoundsException iofe) {
                    System.out.println("fuck");
                } catch (IllegalStateException ise) {
                    System.out.println("fuck");
                }
            }
        }

        System.out.println("\nsIdxLst");
        for (Integer idx : sIdxLst)
            System.out.print(idx + " ");
    }
}
