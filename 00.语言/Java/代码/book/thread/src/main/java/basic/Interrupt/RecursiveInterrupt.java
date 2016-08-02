package basic.Interrupt;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/7/29.
 */
public class RecursiveInterrupt  implements Runnable{

    private String initPath;
    private String fileName;

    public RecursiveInterrupt(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file= new File(initPath);
        if(!file.exists()){
            System.out.println("指定目录不存在！");
        }
        if(file.isDirectory()){
            try{
                DirectoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: 搜索进程已经中断", Thread.currentThread().getName());
            }
        }else{
            System.out.println("搜索目录为文件格式，不合法");
        }
    }

    private void DirectoryProcess(File file) throws InterruptedException{

//        System.out.printf("%s\n", file.getAbsolutePath());

        File files[] = file.listFiles();
        if(null!= files){
            for (int i = 0; i < files.length; i++) {
                File curFile= files[i];
                if(curFile.isDirectory()){
                    DirectoryProcess(curFile);
                }else{
                    FileProcess(curFile);
                }
            }
        }

        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }

    private void FileProcess(File file) throws InterruptedException{
        if(file.getName().equals(fileName)){
            System.out.printf("\t--> %s 搜索到此文件，路径为%s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }else{
//            System.out.printf("%s\n", file.getAbsolutePath());
        }

        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }


    public static void main(String[] args) {
        Thread task = new Thread(new RecursiveInterrupt("E:\\", "4ea228081a3bb373559048f78d22a4cb1.jpg"));
        task.start();
        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}
