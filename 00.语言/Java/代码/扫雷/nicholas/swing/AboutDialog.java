//设置“关于软件”的那个对话框
package nicholas.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
	//JDialog 创建对话框窗口的主要类
public class AboutDialog extends JDialog implements ActionListener {
 
 JButton cancelButton;
 JTextArea textArea;
 JLabel titleLabel;
 JLabel topbarLabel;
 JLabel iconLabel;
 
 public AboutDialog(JFrame frame, String title, String readme, ImageIcon topbar, ImageIcon icon) {
  
  //创建一个具有指定标题、指定所有者 Frame 和 GraphicsConfiguration 的有模式或无模式对话框。	 
  super(frame,"关于 "+title,true);
  getContentPane().setLayout(null);
  
  JTextArea textArea;
  JLabel topbarLabel;
  JLabel iconLabel;
 
  //创建具有指定图像的 JLabel 实例
  topbarLabel = new JLabel(topbar);
  topbarLabel.setBounds(0,0,413,77);
  getContentPane().add(topbarLabel);
  
  iconLabel = new JLabel(icon);
  iconLabel.setBounds(new Rectangle(10, 90, 36, 36));
  getContentPane().add(iconLabel);
  
  JLabel titleLabel = new JLabel("Colinsoft (R) "+title);
  titleLabel.setFont(new Font("Dialog",1,13));
  titleLabel.setBounds(56,84,345,26);
  getContentPane().add(titleLabel);

  	//内部文本
        textArea = new JTextArea();
        textArea.setText(readme);
        //UIManager类跟踪当前的外观及其默认设置        
        //从默认表中返回一种绘制颜色。
        textArea.setBackground(UIManager.getColor("CheckBox.background"));
        //文本区域内容自动换行        
        textArea.setLineWrap(true);
        //设置该文本不可编辑
        textArea.setEditable(false);
        //设置文本插入符的位置
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane();
  
    //用于查看基础信息的“视口”或“观察孔”。
        JViewport viewport = scrollPane.getViewport();
        viewport.add(textArea);
        scrollPane.setBounds(new Rectangle(56, 110, 345, 188));
        scrollPane.setBorder(null);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
      
    //确定按钮
        cancelButton = new JButton("确定");
        cancelButton.setBounds(new Rectangle(340, 315, 60, 23));
        cancelButton.addActionListener(this);
        getContentPane().add(cancelButton);
        
    //分割线（合二为一）
        JLabel separator = new JLabel();
        separator.setBounds(60,307,340,1);
        //创建一个具有凸出斜面边缘的边框，将组件当前背景色的较亮的色度用于突出显示，较暗的色度用于阴影
        separator.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(separator);
        separator = new JLabel();
        separator.setBounds(60,308,340,1);
        separator.setBorder(BorderFactory.createLoweredBevelBorder());
        getContentPane().add(separator);   
             
    //设置总窗口大小
        setSize(419,378);
        //设置此窗口相对于指定组件的位置。如果此组件当前未显示，或者 c 为 null，则此窗口位于屏幕的中央。
        //如果该组件的底部在视线以外，则将该窗口放置在 Component 最接近窗口中心的一侧。
        //因此，如果 Component 在屏幕的右部，则 Window 将被放置在左部，反之亦然。 
        setLocationRelativeTo(frame);
        setResizable(false);
        show();
 }
 
 	public void actionPerformed(ActionEvent e) {
 		dispose();
 	}
}