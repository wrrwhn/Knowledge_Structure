//�Զ���ɨ����
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
		
		//����һ������ָ�������ָ�������� Frame ����ģʽ����ģʽ�Ի���
		super(frame,"�Զ�������",true);
		getContentPane().setLayout(null);
		
		JLabel tempLabel = new JLabel("�߶�:");
		tempLabel.setBounds(10,10,30,20);
		
		heightField = new JTextField(""+levelInfo.getXBound());
		heightField.setBounds(50,10,40,20);
		
		getContentPane().add(tempLabel,null);
		getContentPane().add(heightField,null);
		
		tempLabel = new JLabel("���:");
		tempLabel.setBounds(10,40,30,20);
		
		widthField = new JTextField(""+levelInfo.getYBound());
		widthField.setBounds(50,40,40,20);
		
		getContentPane().add(tempLabel,null);
		getContentPane().add(widthField,null);
		
		tempLabel = new JLabel("����:");
		tempLabel.setBounds(10,70,30,20);
		
		mineField = new JTextField(""+levelInfo.getMineCount());
		mineField.setBounds(50,70,40,20);
		
		getContentPane().add(tempLabel,null);
		getContentPane().add(mineField,null);

		confirmButton = new JButton("ȷ��");
		confirmButton.addActionListener(this);
		confirmButton.setBounds(100,10,60,25);
		getContentPane().add(confirmButton,null);
		
		cancelButton = new JButton("ȡ��");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(100,45,60,25);
		getContentPane().add(cancelButton,null);
		
        setSize(180,137);
        //���ô˴��������ָ�������λ��
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
		//dispose()�ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ
		dispose();
	}
	
	public static LevelInfo getUserLevel(JFrame frame, LevelInfo levelInfo) {
		CustomDialog dialog = new CustomDialog(frame, levelInfo);
		return level;
	}
}
