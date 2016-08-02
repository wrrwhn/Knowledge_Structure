package packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class GarageDoorOpenCommand implements Command {

    GarageDoor door;

    public GarageDoorOpenCommand() {
    }

    public GarageDoorOpenCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }
}
