package com.yao.study.designPatterns.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yao on 2015/5/7.
 */
public class NoOwnerInvocationHandler implements InvocationHandler {

    IPersonBean person;

    public NoOwnerInvocationHandler(IPersonBean personBean) {
        this.person = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try{
            if(method.getName().equals("setHotOrNotRating")){
                method.invoke(person, args);
            }else if (method.getName().startsWith("get")) {
                return method.invoke(person, args);
            }else{
                throw new IllegalAccessException();
            }
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }

        return null;
    }
}
