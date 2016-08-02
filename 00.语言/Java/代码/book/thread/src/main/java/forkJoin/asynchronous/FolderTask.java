package forkJoin.asynchronous;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Yao on 2015/12/2.
 */
public class FolderTask extends RecursiveTask<List<String>> {

    private String path;
    private String extension;

    public FolderTask(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {

        List<String> list= new ArrayList<String>();
        List<FolderTask> tasks= new ArrayList<FolderTask>();

        File file= new File(path);
        if(file.isDirectory()) {
            File fileList[] = file.listFiles();

            if (null!= fileList&& fileList.length> 0){
                for (int i = 0; i < fileList.length; i++) {
                    File f= fileList[i];
                    if(f.isDirectory()){
                        FolderTask task= new FolderTask(f.getAbsolutePath(), extension);
                        task.fork();
                        tasks.add(task);
                    }else{
                        if(checkFile(f.getName()))
                            list.add(f.getAbsolutePath());
                    }
                }
            }
        }

        if(tasks.size()> 50){
            System.out.printf("%s: %d tasks ran \n", file.getAbsolutePath(), tasks.size());
        }

        addResultFromTasks(list, tasks);

        return list;
    }

    private void addResultFromTasks(List<String> list, List<FolderTask> tasks) {
        for(FolderTask task : tasks)
            list.addAll(task.join());
    }

    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
