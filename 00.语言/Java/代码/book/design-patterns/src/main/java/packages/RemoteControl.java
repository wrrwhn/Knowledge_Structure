package packages;

import java.util.Arrays;

/**
 * Created by Yao on 2015/3/17.
 */
public class RemoteControl {

    Command[] onCommands;
    Command[] offCommands;


    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand= new NoCommand();
        for(int i=0; i<7; i++){
            onCommands[i]= noCommand;
            offCommands[i]= noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot]= onCommand;
        offCommands[slot]= offCommand;
    }

    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
    }

    public void doAll(){
        for(int i=0; i< 7; i++){
            onButtonWasPushed(i);
            offButtonWasPushed(i);
        }
    }

    @Override
    public String toString() {
        return "RemoteControl{" +
                "onCommands=" + Arrays.toString(onCommands) +
                ", offCommands=" + Arrays.toString(offCommands) +
                '}';
    }
}
