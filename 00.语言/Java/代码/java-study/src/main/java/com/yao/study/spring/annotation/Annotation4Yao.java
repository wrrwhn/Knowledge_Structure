package com.yao.study.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 注解实现类
 *
 * Created by Administrator on 2014/10/17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation4Yao {
    String str() default "Hello World for Annotation!";
    int[] intAry() default {1, 2, 3, 4};
    ElementType type();
    ValueAnnotation valueAnnotation() default @ValueAnnotation(value = "WOW!");
    Class classType() default String.class;
}
