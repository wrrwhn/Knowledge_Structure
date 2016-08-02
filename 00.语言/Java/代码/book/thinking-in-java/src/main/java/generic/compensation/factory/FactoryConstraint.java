package generic.compensation.factory;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class FactoryConstraint {

    public static void main(String[] args) {

        new Foo<Integer>(new IntegerFactory());
        new Foo<Widget>(new Widget.Factory());
    }
}
