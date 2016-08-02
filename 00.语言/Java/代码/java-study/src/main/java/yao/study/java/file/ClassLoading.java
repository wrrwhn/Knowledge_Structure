package yao.study.java.file;

/**
 * Created by Administrator on 2014/12/30.
 */
public class ClassLoading implements java.io.Serializable{

//java.lang:type=ClassLoading
    private long loadedClassCount;
    private long totalLoadedClassCount;
    private long unloadedClassCount;
    private boolean verbose;
//java.lang:type=Compilation
    private long totalCompilationTime;

    public ClassLoading() {
        this.loadedClassCount = 1;
        this.totalLoadedClassCount = 2;
        this.unloadedClassCount = 3;
        this.verbose = true;
        this.totalCompilationTime = 4;
    }

    public ClassLoading(long loadedClassCount, long totalLoadedClassCount, long unloadedClassCount, boolean verbose, long totalCompilationTime) {
        this.loadedClassCount = loadedClassCount;
        this.totalLoadedClassCount = totalLoadedClassCount;
        this.unloadedClassCount = unloadedClassCount;
        this.verbose = verbose;
        this.totalCompilationTime = totalCompilationTime;
    }

    public long getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(long loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public long getTotalCompilationTime() {
        return totalCompilationTime;
    }

    public void setTotalCompilationTime(long totalCompilationTime) {
        this.totalCompilationTime = totalCompilationTime;
    }

    @Override
    public String toString() {
        return "ClassLoading{" +
                "loadedClassCount=" + loadedClassCount +
                ", totalLoadedClassCount=" + totalLoadedClassCount +
                ", unloadedClassCount=" + unloadedClassCount +
                ", verbose=" + verbose +
                ", totalCompilationTime=" + totalCompilationTime +
                '}';
    }
}
