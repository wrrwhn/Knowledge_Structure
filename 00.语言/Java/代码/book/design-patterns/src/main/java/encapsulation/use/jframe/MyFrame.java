package encapsulation.use.jframe;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yao on 2015/4/7.
 */
public class MyFrame extends JFrame {

    public MyFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(300, 300);
        this.setVisible(true);
    }

    public void paint(Graphics graphics){
        super.paint(graphics);
        String msg= "MyFrame is coming";
        graphics.drawString(msg, 100, 100);
    }

    public static void main(String[] args){
        MyFrame myFrame= new MyFrame("Hello to my frame");
    }
}
