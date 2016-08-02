package singleton;

/**
 * Created by Yao on 2015/3/15.
 */
public class Singleton {

    //若创建消耗不大，可直接在此处进行实例
    private static Singleton singleton;

    //设置构造方法为私有实现
    private Singleton() {
    }

    //使用静态单一接口进行对象创建
    public static Singleton getInstance(){
        if(null== singleton){
            singleton= new Singleton();
        }
        return singleton;
    }
}
