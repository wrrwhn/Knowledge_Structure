//���á�������������Ǹ��Ի���
package nicholas.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
	//JDialog �����Ի��򴰿ڵ���Ҫ��
public class AboutDialog extends JDialog implements ActionListener {
 
 JButton cancelButton;
 JTextArea textArea;
 JLabel titleLabel;
 JLabel topbarLabel;
 JLabel iconLabel;
 
 public AboutDialog(JFrame frame, String title, String readme, ImageIcon topbar, ImageIcon icon) {
  
  //����һ������ָ�����⡢ָ�������� Frame �� GraphicsConfiguration ����ģʽ����ģʽ�Ի���	 
  super(frame,"���� "+title,true);
  getContentPane().setLayout(null);
  
  JTextArea textArea;
  JLabel topbarLabel;
  JLabel iconLabel;
 
  //��������ָ��ͼ��� JLabel ʵ��
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

  	//�ڲ��ı�
        textArea = new JTextArea();
        textArea.setText(readme);
        //UIManager����ٵ�ǰ����ۼ���Ĭ������        
        //��Ĭ�ϱ��з���һ�ֻ�����ɫ��
        textArea.setBackground(UIManager.getColor("CheckBox.background"));
        //�ı����������Զ�����        
        textArea.setLineWrap(true);
        //���ø��ı����ɱ༭
        textArea.setEditable(false);
        //�����ı��������λ��
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane();
  
    //���ڲ鿴������Ϣ�ġ��ӿڡ��򡰹۲�ס���
        JViewport viewport = scrollPane.getViewport();
        viewport.add(textArea);
        scrollPane.setBounds(new Rectangle(56, 110, 345, 188));
        scrollPane.setBorder(null);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
      
    //ȷ����ť
        cancelButton = new JButton("ȷ��");
        cancelButton.setBounds(new Rectangle(340, 315, 60, 23));
        cancelButton.addActionListener(this);
        getContentPane().add(cancelButton);
        
    //�ָ��ߣ��϶�Ϊһ��
        JLabel separator = new JLabel();
        separator.setBounds(60,307,340,1);
        //����һ������͹��б���Ե�ı߿򣬽������ǰ����ɫ�Ľ�����ɫ������ͻ����ʾ���ϰ���ɫ��������Ӱ
        separator.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(separator);
        separator = new JLabel();
        separator.setBounds(60,308,340,1);
        separator.setBorder(BorderFactory.createLoweredBevelBorder());
        getContentPane().add(separator);   
             
    //�����ܴ��ڴ�С
        setSize(419,378);
        //���ô˴��������ָ�������λ�á�����������ǰδ��ʾ������ c Ϊ null����˴���λ����Ļ�����롣
        //���������ĵײ����������⣬�򽫸ô��ڷ����� Component ��ӽ��������ĵ�һ�ࡣ
        //��ˣ���� Component ����Ļ���Ҳ����� Window �����������󲿣���֮��Ȼ�� 
        setLocationRelativeTo(frame);
        setResizable(false);
        show();
 }
 
 	public void actionPerformed(ActionEvent e) {
 		dispose();
 	}
}