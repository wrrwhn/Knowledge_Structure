package packages;

/**
 * Created by Yao on 2015/3/17.
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Do nothing");
    }
}
