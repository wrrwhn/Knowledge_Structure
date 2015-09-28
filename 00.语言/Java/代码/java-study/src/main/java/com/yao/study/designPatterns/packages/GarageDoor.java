package com.yao.study.designPatterns.packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class GarageDoor {

    private String locate;

    public GarageDoor(String locate) {
        this.locate = locate;
    }

    public void open(){
        System.out.println("GarageDoor open");
    }
    public void close(){
        System.out.println("GarageDoor close");
    }

    @Override
    public String toString() {
        return "GarageDoor{" +
                "locate='" + locate + '\'' +
                '}';
    }
}
