//自定义扫雷区
package nicholas.game.mine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomDialog extends JDialog implements ActionListener {
	
	private JTextField widthField;
	private JTextField heightField;
	private JTextField mineField;
	
	private JButton confirmButton;
	private JButton cancelButton;
	
	private static LevelInfo level;
	
	public CustomDialog(Frame frame, LevelInfo levelInfo) {
		
		//创建一个具有指定标题和指定所有者 Frame 的有模式或无模式对话框
		super(frame,"自定义雷区",true);
		getContentPane().setLayout(null);
		
		JLabel tempLabel = new JLabel("高度:");
		tempLabel.setBounds(10,10,30,20);
		
		heightField = new JTextField(""+levelInfo.getXBound());
		heightField.setBounds(50,10,40,20);
		
		getContentPane().add(tempLabel,null);
		getContentPane().add(heightField,null);
		
		tempLabel = new JLabel("宽度:");
		tempLabel.setBounds(10,40,30,20);
		
		widthField = new JTextField(""+levelInfo.getYBound());
		widthField.setBounds(50,40,40,20);
		
		getContentPane().add(tempLabel,null);
		getContentPane().add(widthField,null);
		
		tempLabel = new JLabel("雷数:");
		tempLabel.setBounds(10,70,30,20);
		
		mineField = new JTextField(""+levelInfo.getMineCount());
		mineField.setBounds(50,70,40,20);
		
		getContentPane().add(tempLabel,null);
		getContentPane().add(mineField,null);

		confirmButton = new JButton("确定");
		confirmButton.addActionListener(this);
		confirmButton.setBounds(100,10,60,25);
		getContentPane().add(confirmButton,null);
		
		cancelButton = new JButton("取消");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(100,45,60,25);
		getContentPane().add(cancelButton,null);
		
        setSize(180,137);
        //设置此窗口相对于指定组件的位置
		setLocationRelativeTo(frame);
        setResizable(false);
        show();
	}
	
	public void actionPerformed(ActionEvent e) {
		level = null;
		if(e.getSource()==confirmButton) {
			int x = Integer.parseInt(heightField.getText());
			int y = Integer.parseInt(widthField.getText());
			int m = Integer.parseInt(mineField.getText());
			level = new LevelInfo(x,y,m);
		}
		//dispose()释放由此 Window、其子组件及其拥有的所有子组件所使用的所有本机屏幕资源
		dispose();
	}
	
	public static LevelInfo getUserLevel(JFrame frame, LevelInfo levelInfo) {
		CustomDialog dialog = new CustomDialog(frame, levelInfo);
		return level;
	}
}
