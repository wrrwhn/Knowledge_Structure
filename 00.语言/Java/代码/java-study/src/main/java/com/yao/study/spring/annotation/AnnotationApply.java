package com.yao.study.spring.annotation;

import java.lang.annotation.ElementType;

/**
 * Created by Administrator on 2014/10/17.
 */
public class AnnotationApply {

    /***
     * 为注解实现类补充输出方法和相应的注解值
     */
    @Annotation4Yao(type = ElementType.FIELD)
    public void output(){
        System.out.println("AnnotationTest.output() is invoke!");
    }
}
