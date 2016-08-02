package generic.compensation.factory;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class Widget {

    /**
     * 内建-隐式工厂
     */
    public static class Factory implements IFactory<Widget> {

        @Override
        public Widget create() {
            return new Widget();
        }
    }
}
