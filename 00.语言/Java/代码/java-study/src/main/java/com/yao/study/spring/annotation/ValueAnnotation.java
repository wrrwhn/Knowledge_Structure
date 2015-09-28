package com.yao.study.spring.annotation;

/**
 * 基本注解，内部填充值
 *
 * Created by Administrator on 2014/10/17.
 */
public @interface ValueAnnotation {
    String value() default "ValueAnnotation";
}
