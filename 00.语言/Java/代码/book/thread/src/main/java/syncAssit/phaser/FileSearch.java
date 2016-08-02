package syncAssit.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yao on 2015/9/26.
 */
public class FileSearch implements Runnable {

    private String filePath;
    private String extend;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String filePath, String extend, Phaser phaser) {
        this.filePath = filePath;
        this.extend = extend;
        this.phaser = phaser;
        results = new ArrayList<String>();
    }

    @Override
    public void run() {

        try{
            phaser.arriveAndAwaitAdvance();
            File file = new File(filePath);
            if(file.exists() && file.isDirectory())
                deepProc(file);
            if(!checkResults())
                return;
            filterResults();
            if(!checkResults())
                return;
            showInfos();
            phaser.arriveAndAwaitAdvance();
            System.out.printf("-> %s work finished.\n", Thread.currentThread().getName());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void showInfos() {
        if(results.isEmpty()){
            System.out.printf("\t-->%s phase %d is empty\n", Thread.currentThread().getName(), phaser.getPhase());
        }else{
            System.out.printf("\t-->%s phase %d results list:\n", Thread.currentThread().getName(), phaser.getPhase());
            for (String path : results){
                System.out.println("\t\t"+ path);
            }
        }
        phaser.arriveAndAwaitAdvance();
    }

    private void filterResults() {

        List<String> list = new ArrayList<String>();
        long now= new Date().getTime();

        for(String path : results){
            File file= new File(path);
            if(now- file.lastModified()< TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
                list.add(path);
        }

        results= list;
    }

    private boolean checkResults() {

        if(results.isEmpty()){
            System.out.printf("\t%s phase %d has 0 results\n", Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        }else{
            System.out.printf("\t%s phase %d has %d results\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void deepProc(File file) {
        File[] files = file.listFiles();
        for(File f : files){
            if(f.isDirectory()){
                deepProc(f);
            }else{
                fileProc(f);
            }
        }
    }

    private void fileProc(File file) {
        if(file.getName().endsWith(extend)){
            results.add(file.getAbsolutePath());
        }
    }
}
