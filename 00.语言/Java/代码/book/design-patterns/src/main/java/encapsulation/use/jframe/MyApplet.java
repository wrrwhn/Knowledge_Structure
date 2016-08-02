package encapsulation.use.jframe;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Yao on 2015/4/8.
 */
public class MyApplet extends Applet {

    String msg;

    public void init(){
        msg= "hello world";
        repaint();
    }

    public void start(){
        msg= "Starting ";
        repaint();
    }

    public void stop(){
        msg= "Stopping ";
        repaint();
    }

    public void destroy(){
        System.out.println("Destroying");
    }

    public void paint(Graphics graphics){
        graphics.drawString(msg, 5, 15);
    }
}
