package generic.clean;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 *      javac GenericHolder.java
 *      javap -c GenericHolder.class
 * Other:
 */
public class GenericHolder<T> {

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        GenericHolder<String> holder = new GenericHolder();
        holder.setObj("item");
        String item = holder.getObj();
    }
}
