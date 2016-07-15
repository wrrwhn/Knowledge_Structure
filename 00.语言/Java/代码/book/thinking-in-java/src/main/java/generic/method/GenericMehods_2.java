package generic.method;

/**
 * Creator: Yao
 * Date:    2016/7/14
 * For:
 * Other:
 */
public class GenericMehods_2 {

    public <T> void printClass(T t, String in) {
        System.out.println(in + t.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMehods_2 genericMehods = new GenericMehods_2();
        genericMehods.printClass(1, "WTF ");
        genericMehods.printClass(1L, "WTF ");
        genericMehods.printClass(1F, "WTF ");
        genericMehods.printClass(1D, "WTF ");
        genericMehods.printClass(1.1, "WTF ");
        genericMehods.printClass(true, "WTF ");
        genericMehods.printClass('a', "WTF ");
        genericMehods.printClass("String", "WTF ");
    }
}
