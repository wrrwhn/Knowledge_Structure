package com.yao.study.designPatterns.observer.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yao on 2015/2/5.
 */
public class SwingObserverExample {
    JFrame frame;

    public static void main(String[] args){

    }

    public void go(){
        frame= new JFrame();

        JButton button= new JButton("Do it?");
        button.addActionListener(new AngelListener());
        button.addActionListener(new DevilListener());
        frame.getContentPane().add(BorderLayout.CENTER, button);
    }

    class AngelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it~");
        }
    }

    class DevilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Do it, why not~");
        }
    }
}
