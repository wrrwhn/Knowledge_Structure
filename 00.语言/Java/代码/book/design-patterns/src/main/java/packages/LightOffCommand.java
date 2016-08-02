package packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class LightOffCommand implements Command {

    Light ligth;

    public LightOffCommand(Light ligth) {
        this.ligth = ligth;
    }

    @Override
    public void execute() {
        ligth.off();
    }
}
