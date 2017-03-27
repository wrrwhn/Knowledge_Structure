package com.yao.study.java.jni;

/**
 * Creator: Yao
 * Date:    2016/12/5
 * For:     step-1
 * Other:
 */
public class JNIProxy {

    static {
        System.loadLibrary("jni_msg");
    }

    public static native void print(String msg);

    public static void main(String[] args) {

        String msg = null;

        if (null == args || args.length == 0)
            msg = "hello world";
        else
            msg = args[0];

        print(msg);
    }
}
