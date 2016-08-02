package generic.clean;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 *      javac SimpleHolder.java
 *      javap -c SimpleHolder.class
 * Other:
 */
public class SimpleHolder {

    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        SimpleHolder holder = new SimpleHolder();
        holder.setObj("Item");
        String item = (String) holder.getObj();
    }
}
