package com.yao.study.designPatterns.packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class GarageDoorCloseCommand implements Command {

    GarageDoor door;

    public GarageDoorCloseCommand() {
    }

    public GarageDoorCloseCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }
}
