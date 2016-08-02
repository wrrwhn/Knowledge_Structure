package com.yao.study.java.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {

    //测试变更属性，其中为了直观调用属性属性设置为public类型
    public double field;

    public static void main(String[] args) {
//        printClssInfo();
//        callClassMethod();
//        callFieldChange();
        List2Obj();
    }

    /***
     * 构造函数（无参-默认& 带参）
     */
    public ReflectionTest() {}
    public ReflectionTest(Double field) {
        this.field = field;
    }

    /**
     *  动态获取数据对象，可将多维数组的部分结构拼成对象来调用
     */
    public static void List2Obj(){
        int dims[] = new int[]{5, 10, 15};
        Object arr = Array.newInstance(Integer.TYPE, dims);
        Object arrobj = Array.get(arr, 3);
        Class cls = arrobj.getClass().getComponentType();
        System.out.println(cls.getSimpleName());
        arrobj = Array.get(arrobj, 5);
        Array.setInt(arrobj, 10, 37);
        int arrcast[][][] = (int[][][]) arr;
        System.out.println(arrcast[3][5][10]);
    }

    /***
     * 调用参数的方法进行属性更新
     */
    public static void callFieldChange(){
        Class c = null;
        try {
            c = Class.forName("ReflectionTest");
            Field field= c.getField("field");
            ReflectionTest reflect= new ReflectionTest(12.34D);
            System.out.println(reflect.field);
            field.setDouble(reflect, 43.21);
            System.out.println(reflect.field);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * 专门用于提供反射调用
     * @param a 加数1
     * @param b 加数2
     * @return  a+b的结果
     */
    public int add(int a, int b){
        return a+ b;
    }

    /***
     * 调用本方法内的ADD方法
     *      Constructor方式与之相似
     */
    public static void callClassMethod(){
        Class c = null;
        try {
            c = Class.forName("ReflectionTest");

            //指定方法和参数类型以获取类中对应的方法
            Class types[]= new Class[2];
            types[0]= Integer.TYPE;
            types[1]= Integer.TYPE;
            Method method= c.getMethod("add", types);

            //针对各参数位填充以实际数值
            Object args[]= new Object[2];
            args[0]= new Integer(1);
            args[1]= new Integer(2);
            Integer retVal= (Integer)method.invoke(c.newInstance(), args);
            System.out.print(retVal);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * 输出String类大致文档结构
     */
    public static void printClssInfo(){
        Class c = null;
        try {
            c = Class.forName("java.lang.String");
            System.out.println("package " + c.getPackage().getName() + ";");
            System.out.print(Modifier.toString(c.getModifiers()) + " ");
            System.out.print("class " + c.getSimpleName() + " ");
            if (c.getSuperclass() != Object.class) {
                System.out.print("extends " + c.getSuperclass().getSimpleName());
            }
            Class[] inters = c.getInterfaces();
            if (inters.length > 0) {
                System.out.print("implements ");
                for (int i = 0; i < inters.length; i++) {
                    System.out.print(inters[i].getSimpleName());
                    if (i < inters.length - 1) {
                        System.out.print(",");
                    }
                }
            }
            System.out.println("{");
            printFields(c);
            printMethods(c);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 打印类的所有参数
     * @param c
     */
    public static void printFields(Class c) {
        Field[] field = c.getDeclaredFields();
        if (field.length > 0) {
            for (int i = 0; i < field.length; i++) {
                System.out.println(Modifier.toString(field[i].getModifiers()) + " " + field[i].getType().getSimpleName() + " " + field[i].getName() + ";");
            }
        }
    }

    /***
     * 打印类的所有方法
     * @param c
     */
    public static void printMethods(Class c) {
        Method[] method = c.getDeclaredMethods();
        if (method.length > 0) {
            for (int i = 0; i < method.length; i++) {
                Class[] parameter = method[i].getParameterTypes();
                System.out.print(Modifier.toString(method[i].getModifiers()) + " " + method[i].getReturnType().getSimpleName() + " " + method[i].getName() + "(");
                for (int j = 0; j < parameter.length; j++) {
                    System.out.print(parameter[j].getSimpleName() + " args");
                    if (j != parameter.length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.print(") ");
                Class exception[] = method[i].getExceptionTypes();

                if (exception.length > 0) {
                    System.out.print("throws ");
                    for (int j = 0; j < exception.length; j++) {
                        System.out.print(exception[j].getSimpleName());
                    }
                }
                System.out.println("{");
                System.out.println("\t... ...");
                System.out.println("}");
            }

        }
    }
}