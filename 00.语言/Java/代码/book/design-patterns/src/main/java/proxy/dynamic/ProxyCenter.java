package proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * Created by Yao on 2015/5/7.
 */
public class ProxyCenter {

    public static IPersonBean getOwnerProxy(PersonBeanImpl person){
        return (IPersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person)
        );
    }
    public static IPersonBean getNoownerProxy(PersonBeanImpl person){
        return (IPersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NoOwnerInvocationHandler(person)
        );
    }
}
