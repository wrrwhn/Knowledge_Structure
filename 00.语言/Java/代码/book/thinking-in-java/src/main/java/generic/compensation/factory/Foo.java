package generic.compensation.factory;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class Foo<T> {

    private T obj;

    /**
     * 默认调用工厂
     * @param factory
     * @param <F>
     */
    public <F extends IFactory<T>> Foo(F factory) {
        obj = factory.create();
    }
}