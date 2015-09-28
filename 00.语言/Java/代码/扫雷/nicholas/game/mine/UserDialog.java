//覆盖新的记录或者保持原有的记录
package nicholas.game.mine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserDialog extends JDialog implements ActionListener {

	//确定按钮	
	private JButton confirmButton;
	private JTextField nameField;
	private String[] level = {"初级","中级","高级"};
	private static String name;
	
	public UserDialog(JFrame frame, int l, String n) {
		
		super(frame, "新记录",true);
        getContentPane().setLayout(null);
		
		JLabel textLabel = new JLabel("已破"+level[l]+"记录，");
        textLabel.setBounds(30,5,100,20);
        getContentPane().add(textLabel,null);
        
		textLabel = new JLabel("请留尊姓大名。");
        textLabel.setBounds(30,25,100,20);
        getContentPane().add(textLabel,null);
		
		nameField = new JTextField(n);
		nameField.setBounds(10,60,120,20);
		//选择此文本组件中的所有文本
		nameField.selectAll();
        getContentPane().add(nameField,null);
		
		confirmButton = new JButton("确定");
		confirmButton.addActionListener(this);
		confirmButton.setBounds(40,90,60,25);
        getContentPane().add(confirmButton,null);
        
        //setUndecorated(true);可以将窗体的标题栏隐去,   
        //但窗体的显示位置为屏幕的右上角,并且窗体不可以被移动. 
        this.setUndecorated(true);
        setSize(145,130);
		setLocationRelativeTo(frame);
        setResizable(false);
        show();
	}
	
	public void actionPerformed(ActionEvent e) {
		name = nameField.getText();
		//释放该组件所占用的屏幕资源
		dispose();
	}
	
	public static String showInputNameDialog(JFrame frame, int l, String n) {
		UserDialog dialog = new UserDialog(frame, l, n);
		return name;
	}
}