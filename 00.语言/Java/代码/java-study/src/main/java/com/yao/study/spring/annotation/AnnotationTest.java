package com.yao.study.spring.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2014/10/17.
 */
public class AnnotationTest {

    /**
     * 输出注解的方法、注解绑定值及注解类名称
     * @param args
     */
    public static void main(String[] args){
        try {
            AnnotationApply apply= new AnnotationApply();
            Method method= AnnotationApply.class.getMethod("output",null);
            if(AnnotationTest.class.isAnnotationPresent(ValueAnnotation.class)){
                System.out.println("AnnotationTest contain ValueAnnotation!");
            }

            if (method.isAnnotationPresent(Annotation4Yao.class))
            {
                method.invoke(apply, null); //调用output方法
                //获取方法上注解@MyAnnotation的信息
                Annotation4Yao annotation4Yao = method.getAnnotation(Annotation4Yao.class);
                System.out.println("Annotation4Yao= {\n" +
                        "\tstr: " + annotation4Yao.str()+
                        ",\n\tintAtr.length:" + annotation4Yao.intAry().length+
                        ", \n\ttype: " + annotation4Yao.type()+
                        ", \n\tvalueAnnotation.value: " + annotation4Yao.valueAnnotation().value()+
                        ", \n\tclassType=" + annotation4Yao.classType()+
                        " \n}");
            }
            //得到output方法上的所有注解，当然是被RetentionPolicy.RUNTIME修饰的
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations){
                System.out.println(annotation.annotationType().getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
