package generic.compensation.factory;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:     显式工厂
 * Other:
 */
public class IntegerFactory implements IFactory<Integer> {

    @Override
    public Integer create() {
        return new Integer(0);
    }
}
