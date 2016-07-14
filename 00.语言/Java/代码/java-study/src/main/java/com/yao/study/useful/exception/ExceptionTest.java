package com.yao.study.useful.exception;

import java.io.IOException;

public class ExceptionTest {

    public static void main(String[] args) {
        Exception exception= new Exception();
        IOException ioException= new IOException();

        if(ioException instanceof Exception)
            System.out.println("IOException");
    }
}
