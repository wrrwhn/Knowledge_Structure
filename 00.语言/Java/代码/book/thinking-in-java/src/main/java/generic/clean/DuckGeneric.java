package generic.clean;

/**
 * Creator: Yao
 * Date:    2016/7/23
 * For:
 * Other:
 */
public class DuckGeneric implements IGeneric {
    @Override
    public void say() {
        System.out.println("Duck say");
    }

    @Override
    public void fly() {
        System.out.println("Duck fly");
    }

    public void look(){
        System.out.println("Duck is looking at you");
    }
}
