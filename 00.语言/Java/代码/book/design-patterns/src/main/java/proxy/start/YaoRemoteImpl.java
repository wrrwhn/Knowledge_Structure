package proxy.start;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Yao on 2015/5/5.
 *
 * 步骤二：制作远程实现
 */

/**
 * 继承 UnicastRemoteObject： 成为远程服务对象
 */
public class YaoRemoteImpl extends UnicastRemoteObject implements YaoRemote {

    /***
     * 抛出构建远程对象时可能存在的异常
     * @throws RemoteException
     */
    protected YaoRemoteImpl() throws RemoteException {
    }

    /***
     * 确保实现接口的所有方法
     * @return
     * @throws RemoteException
     */
    @Override
    public String doSome() throws RemoteException {
        return "Server is doing something";
    }

    public static void main(String[] args) {
        try{
            YaoRemote yaoRemote= new YaoRemoteImpl();
            Naming.rebind("YaoRemote", yaoRemote);      // 需提前启动 RMI Register
            while(true){
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
//            System.out.println("哈哈哈哈哈哈哈哈，异常而已啦");
        }
    }
}
