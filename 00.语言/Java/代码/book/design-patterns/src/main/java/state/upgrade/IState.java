package state.upgrade;

/**
 * Created by Yao on 2015/4/26.
 */
public interface IState {

    public void insertQuarter() ;

    public void ejectQuarter();

    public void turnCrank();

    public void dispense();

}
