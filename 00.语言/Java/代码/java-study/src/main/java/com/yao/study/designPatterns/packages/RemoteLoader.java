package com.yao.study.designPatterns.packages;

/**
 * Created by Yao on 2015/3/17.
 */
public class RemoteLoader {

    public static void main(String[] args){

        RemoteControl control= new RemoteControl();

        Light light= new Light("Home");
        GarageDoor door= new GarageDoor("Home");
        Stereo stereo= new Stereo("Home");

        LightOnCommand lightOnCommand= new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        GarageDoorOpenCommand garageDoorOpenCommand= new GarageDoorOpenCommand(door);
        GarageDoorCloseCommand garageDoorCloseCommand= new GarageDoorCloseCommand(door);
        StereoOnWithCDCommand stereoOnWithCDCommand= new StereoOnWithCDCommand(stereo);
        StereoOffWithCDCommand stereoOffWithCDCommand= new StereoOffWithCDCommand(stereo);

        control.setCommand(0, lightOnCommand, lightOffCommand);
        control.setCommand(1, garageDoorOpenCommand, garageDoorCloseCommand);
        control.setCommand(2, stereoOnWithCDCommand, stereoOffWithCDCommand);

        control.doAll();

    }
}
