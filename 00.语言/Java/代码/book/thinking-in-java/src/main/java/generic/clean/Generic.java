package generic.clean;

/**
 * Creator: Yao
 * Date:    2016/7/23
 * For:
 * Other:
 */
public class Generic<T extends IGeneric> {

    T obj;

    public Generic(T obj) {
        this.obj = obj;
    }

    public void say() {
        obj.say();
    }

    public void fly() {
        obj.fly();
    }

    public static void main(String[] args) {

        DuckGeneric duckGeneric = new DuckGeneric();
        Generic<DuckGeneric> generic = new Generic<>(duckGeneric);
        generic.say();
        generic.fly();
    }
}
