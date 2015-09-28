package com.yao.study.java.reflection.enums;

/**
 * Created by Administrator on 2014/11/20.
 */
enum  EType {
    HTTP, TCP, UDP
}

public class Switch {
    EType type= EType.HTTP;

    public void change(){
        switch (type){
            case HTTP:
                type= EType.TCP;
                break;
            case TCP:
                type= EType.UDP;
                break;
            case UDP:
                type= EType.HTTP;
                break;
        }
    }

    public void getType(){
        System.out.println(type);
    }
}