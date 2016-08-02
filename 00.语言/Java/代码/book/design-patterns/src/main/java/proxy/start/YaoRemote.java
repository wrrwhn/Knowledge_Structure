package proxy.start;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Yao on 2015/5/5.
 *
 * 步骤一：制作远程接口
 */
/**
 * 继承 Remote 接口，告知 RMI 当前接口为远程调用接口
 */
public interface YaoRemote extends Remote {

    /**
     * return String：变量和返回值均需属于原语类型或可序列化类型 -> 网络传输需要
     *      注：若有相关字段不希望进行序列化，可使用 transient 关键字
     * throws RemoteException：声明 RMI 调用中可能出现的异常，通知使用方进行异常捕获
     */

    public String doSome() throws RemoteException;
}
