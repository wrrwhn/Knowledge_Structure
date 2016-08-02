package start;

/**
 * Created by Yao on 2015/1/28.
 */
public abstract class Duck{
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck(){}

    public void performFly(){
        flyBehavior.fly();
    }
    public void performQuack(){
        quackBehavior.quack();
    }
    public void swim(){
        System.out.println("Duck is swimming~");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior= flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior= quackBehavior;
    }
}
