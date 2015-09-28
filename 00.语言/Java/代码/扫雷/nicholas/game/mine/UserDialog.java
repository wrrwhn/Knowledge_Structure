//�����µļ�¼���߱���ԭ�еļ�¼
package nicholas.game.mine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserDialog extends JDialog implements ActionListener {

	//ȷ����ť	
	private JButton confirmButton;
	private JTextField nameField;
	private String[] level = {"����","�м�","�߼�"};
	private static String name;
	
	public UserDialog(JFrame frame, int l, String n) {
		
		super(frame, "�¼�¼",true);
        getContentPane().setLayout(null);
		
		JLabel textLabel = new JLabel("����"+level[l]+"��¼��");
        textLabel.setBounds(30,5,100,20);
        getContentPane().add(textLabel,null);
        
		textLabel = new JLabel("�������մ�����");
        textLabel.setBounds(30,25,100,20);
        getContentPane().add(textLabel,null);
		
		nameField = new JTextField(n);
		nameField.setBounds(10,60,120,20);
		//ѡ����ı�����е������ı�
		nameField.selectAll();
        getContentPane().add(nameField,null);
		
		confirmButton = new JButton("ȷ��");
		confirmButton.addActionListener(this);
		confirmButton.setBounds(40,90,60,25);
        getContentPane().add(confirmButton,null);
        
        //setUndecorated(true);���Խ�����ı�������ȥ,   
        //���������ʾλ��Ϊ��Ļ�����Ͻ�,���Ҵ��岻���Ա��ƶ�. 
        this.setUndecorated(true);
        setSize(145,130);
		setLocationRelativeTo(frame);
        setResizable(false);
        show();
	}
	
	public void actionPerformed(ActionEvent e) {
		name = nameField.getText();
		//�ͷŸ������ռ�õ���Ļ��Դ
		dispose();
	}
	
	public static String showInputNameDialog(JFrame frame, int l, String n) {
		UserDialog dialog = new UserDialog(frame, l, n);
		return name;
	}
}