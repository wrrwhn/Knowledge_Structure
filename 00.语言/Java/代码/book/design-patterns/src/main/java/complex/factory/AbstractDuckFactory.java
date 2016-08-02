package complex.factory;

/**
 * Created by Yao on 2015/5/9.
 */
public abstract class AbstractDuckFactory {

    public abstract IQuackable createMallardDuck();
    public abstract IQuackable createRedHeadDuck();
    public abstract IQuackable createDuckCall();
    public abstract IQuackable createRubberDuck();
}
