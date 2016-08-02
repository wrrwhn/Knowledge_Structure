package packages;

/**
 * Created by Yao on 2015/3/16.
 */
public class RemoteControlTest {

    public static void main(String[] args){
        SimpleRemoteControl remoteControl= new SimpleRemoteControl();
        Light light= new Light("");
        LightOnCommand lightOn= new LightOnCommand(light);
        GarageDoor door= new GarageDoor("");
        GarageDoorOpenCommand doorOpen= new GarageDoorOpenCommand(door);
        
        remoteControl.setSlot(lightOn);
        remoteControl.buttonWasPressed();
        remoteControl.setSlot(doorOpen);
        remoteControl.buttonWasPressed();
    }
}
