package generic.methods;

/**
 * Creator: Yao
 * Date:    2016/7/14
 * For:
 * Other:
 */
public class GenericMehods {

    public <T> void printClass(T t) {
        System.out.println(t.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMehods genericMehods = new GenericMehods();
        genericMehods.printClass(1);
        genericMehods.printClass(1L);
        genericMehods.printClass(1F);
        genericMehods.printClass(1D);
        genericMehods.printClass(1.1);
        genericMehods.printClass(true);
        genericMehods.printClass('a');
        genericMehods.printClass("String");
    }
}
