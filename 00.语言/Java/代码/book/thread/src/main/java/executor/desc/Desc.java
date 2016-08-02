package executor.desc;

/**
 *
 *  线程执行器
 *      解决：
 *          程序大量运行并发任务时出现如下情况
 *              【1】必须实现所有与 Thread 对象管理相关的代码，如创建、结束和结束获取
 *              【2】创建一个任务时创建一个 Thread 对象，则需执行大量任务时将影响应用程序处理能力
 *              【3】创建过多线程，影响计算机资源的控制管理、系统的负荷过重
 *      优点：
 *          使用线程池技术，避免不断创建和销毁线程导致的系统性能下降
 *          Callable 接口，支持返回结果、通过 Future 接口对象控制 Callable 对象的状态和结果
 *
 */
public class Desc {

    public static void main(String[] args) {

    }
}
