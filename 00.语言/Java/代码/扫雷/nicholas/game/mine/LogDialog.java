//ɨ��Ӣ�۰�����ݺͿ�ʽ

package nicholas.game.mine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogDialog extends JDialog implements ActionListener {
	
	//������¼
	private LevelLog levelLog[];
	private JTextArea logArea;
	private JButton resetButton;
	private JButton confirmButton;
	
	public LogDialog(JFrame frame, LevelLog log[]) {
		
		super(frame, "ɨ��Ӣ�۰�", true);
		
		getContentPane().setLayout(null);
		
		levelLog = log;
		
		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setBackground(UIManager.getColor("CheckBox.background"));
		logArea.setBounds(10,10,160,60);
		getContentPane().add(logArea, null);
					  
		resetButton = new JButton("���¼Ʒ�");
		resetButton.setBounds(10,70,90,25);
		resetButton.addActionListener(this);
		getContentPane().add(resetButton, null);
		
		setTextArea();
		
		confirmButton = new JButton("ȷ��");
		confirmButton.setBounds(105,70,60,25);
		confirmButton.addActionListener(this);
		getContentPane().add(confirmButton, null);
		
        setSize(180,140);
		setLocationRelativeTo(frame);
        setResizable(false);
        show();
	}
	
	private void setTextArea() {
		logArea.setText("������" + levelLog[0].toString()
					  + "�м���" + levelLog[1].toString()
					  + "�߼���" + levelLog[2].toString());
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==resetButton) {
			for(int i=0;i<3;i++) {
				levelLog[i].setDefault();
			}
			setTextArea();
		} else {
			dispose();
		}
	}
}
