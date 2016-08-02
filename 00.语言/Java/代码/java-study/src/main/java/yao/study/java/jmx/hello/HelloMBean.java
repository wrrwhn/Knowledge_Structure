package yao.study.java.reflection.jmx.hello;

/**
 * Created by Administrator on 2014/11/6.
 */
public interface HelloMBean { 	//todoMBean接口 	注：规范要求接口必须以 dosome+ MBean为名称
    public String getName();		//只读
    public int getCacheSize(); 		//可读可写
    public void setCacheSize(int size);
}
