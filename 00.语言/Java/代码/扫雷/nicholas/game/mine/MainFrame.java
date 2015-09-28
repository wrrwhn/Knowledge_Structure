package nicholas.game.mine;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import nicholas.awt.LedNumber;
import nicholas.swing.AboutDialog;

public class MainFrame extends JFrame implements ActionListener {
	
	//UI components	
	private JLabel mineLabel;
	private JLabel timeLabel;
	private JLabel statusButton;
	private JPanel gridPanel;
	private JPanel statusPanel;
	
	//Dimension��װ��������������Ŀ�Ⱥ͸߶�
	private Dimension gpd;
	private Dimension spd;
	private MineGrid grid[][];
	private boolean mode[][];
	
	//ҳ�߿հ� 
	private final int margin = 7;
	private final int titleh = 52;
	
	private int xBound;
	private int yBound;
	private int mineCount;
	private int showCount;		//�򿪵ĸ�������
	private int leftCount;		//ʣ��ģ��Լ���Ϊ�жϵĵ��׸���
	private int timepassed;
	private boolean firstClick;
	private boolean markCheck;
	
	
	//Menu Components
	private JMenuItem startItem;
	private JMenuItem exitItem;
	private JMenuItem logItem;
	private JMenuItem aboutItem;
		//����ѡ�񣬵ȼ�
	private JRadioButtonMenuItem levelItem[];
		//��ѡ����Ƿ��б��
	private JCheckBoxMenuItem markCheckItem;
		
	
	//Game informations
	private LevelInfo levelInfo;
	private int currentLevel;
	private LevelLog log[];
	
	private LedNumber led;
	
	private GridMouseAdapter gma;
	private StatusMouseAdapter sma;
	private TimeThread timeThread;
	
	//����������
	public MainFrame() {
		//����Ϊɨ��
		super("ɨ��");
		
		//Ĭ�ϵ�ǰ�ȼ�Ϊ0
		currentLevel = 0;
		//��Ӧ�ȼ��ĸ��ָ�������������¼��
		levelInfo = LevelInfo.DEFAULT_LEVEL[currentLevel];
		
		//�����ȼ��ĵ����ݳ�ʼ��
		log = new LevelLog[3];
		for(int i=0;i<3;i++)
			log[i] = new LevelLog();
		
		//read from file
		readLog();
		
		led = new LedNumber();
		
		gma = new GridMouseAdapter();		//���Ӽ�����
		sma = new StatusMouseAdapter();		//״̬��ť�ļ�����
		
		//��ʾ����������
		setMenuBar();		
		setStatusPanel();
		resetPane();
		
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					writeLog();
					System.exit(0);
				}
			}
		);
		
		//����ͼ��Ϊ ����
		setIconImage(ImageFactory.getInstance().getImageicon(16).getImage());
		//����Ϊ���ڲ����϶�
		setResizable(false);
	}
	
	//���ó��򣬴�����������
	public static void main(String args[]) {
		MainFrame application = new MainFrame();
		try {
			//���ٵ�ǰ����ۼ���Ĭ������
			//ʹ�� LookAndFeel �������õ�ǰ��Ĭ�����
			//����ʵ�ֱ���ϵͳ��ۣ������һ������ LookAndFeel ������ƣ����򷵻�Ĭ�ϵĿ�ƽ̨ LookAndFeel �������
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
	}
	
	//���ļ���������ȡ�Զ���ȼ��ļ�¼��Ϣ
	private void readLog() {
		try {
			//File �ļ���Ŀ¼·�����ĳ����ʾ��ʽ
			File logFile = new File("mine.log");
			if(!logFile.exists()) return;
			//���ڽ��ܶ�Ӧ�ļ��ڵ�ͼƬ����
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(logFile));
			for(int i=0;i<3;i++) {
				//�� ObjectInputStream ��ȡ����
				log[i] = (LevelLog)ois.readObject();
			}
			//�� boolean ��ʽ��ȡOIS��
			markCheck = ois.readBoolean();
			currentLevel = ois.readInt();
			//�Զ�������
			//������������е����ݽ��г�ʼ��
			if(currentLevel==3) {
				levelInfo = (LevelInfo)ois.readObject();
			} else {
				levelInfo = LevelInfo.DEFAULT_LEVEL[currentLevel];
			}
			ois.close();
		} catch (Exception e) {System.out.println("read fail");}
	}
	
	//���ļ����洢�Զ���ȼ��ļ�¼����
	private void writeLog() {
		try {
			File logFile = new File("mine.log");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(logFile));
			for(int i=0;i<3;i++) {
				oos.writeObject(log[i]);
			}
			oos.writeBoolean(markCheck);
			oos.writeInt(currentLevel);
			if(currentLevel==3) {
				oos.writeObject(levelInfo);
			}
			oos.close();
		} catch (Exception e) {System.out.println("write fail");}
	}
		
	//��ʾʣ��������ʱ���״̬���Ʊ�־�ı�ǩ
	private void setStatusPanel() {
		
		JPanel temp;
		statusPanel = new JPanel(new BorderLayout());
		
		//ʣ��������ǩ
		mineLabel = new JLabel();
		//����һ�����а���б���Ե�ı߿򣬽������ǰ����ɫ�Ľ�����ɫ������ͻ����ʾ���ϰ���ɫ��������Ӱ��
		mineLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		//FlowLayout  ����һ���µ������ֹ�����������ָ���Ķ��뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		temp = new JPanel(new FlowLayout(1,4,4));
		temp.add(mineLabel);
		temp.setBackground(Color.LIGHT_GRAY);
		statusPanel.add(temp,BorderLayout.WEST);
		
	  //ʱ���ǩ
		timeLabel = new JLabel();
        timeLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		temp = new JPanel(new FlowLayout(1,4,4));
		temp.add(timeLabel);
		temp.setBackground(Color.LIGHT_GRAY);
		statusPanel.add(temp,BorderLayout.EAST);
        
	  //״̬��ť��ǩ
        statusButton = new JLabel();
		statusButton.setBorder(BorderFactory.createRaisedBevelBorder());
		statusButton.addMouseListener(sma);
		temp = new JPanel(new FlowLayout(1,0,4));
		temp.setBackground(Color.LIGHT_GRAY);
		temp.add(statusButton);
		statusPanel.add(temp,BorderLayout.CENTER);
		
		//���øñ�ǩ�ĳ��Ϳ�   ���ں���������ǿɱ��
		statusPanel.setSize(10,37);
		statusPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		//ȡ�õ�ǰstatusPanel�ľ�ȷ�ĳ��Ϳ�
		spd = statusPanel.getSize();
	}
	
	//��ʼ��״̬����������ǩ��һ����ť
	private void resetStatusPanel() {
		mineLabel.setIcon(new ImageIcon(led.getLedImage(leftCount,3)));
		timeLabel.setIcon(new ImageIcon(led.getLedImage(timepassed,3)));
		statusButton.setIcon(ImageFactory.getInstance().getImageicon(17));
	}

	//���������Ͳ�����
	private void setGridPanel() {
		//�ֱ��ŵ�ǰ����ŵ��������ѻ��ĳ��͸�
		xBound = levelInfo.getXBound();
		yBound = levelInfo.getYBound();
		mineCount = levelInfo.getMineCount();
		MineGrid.xBound = this.xBound;
		MineGrid.yBound = this.yBound;
		grid = new MineGrid[xBound][yBound];
		//�ж��Ƿ���
		mode = new boolean[xBound][yBound];
		
		gridPanel = new JPanel();
		gridPanel.setBackground(Color.GRAY);
		//initialize grid panel
		gridPanel.setLayout(null);
		
		//��һ���������ӵ���
		//xBound ��		yBound  ��
		for(int x = 0; x < xBound; x++) {
			for(int y =0; y <yBound; y++) {
				grid[x][y] = new MineGrid(x,y);
				grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(9));
				grid[x][y].setBounds(1+y*MineGrid.SIZE,x*MineGrid.SIZE,MineGrid.SIZE,MineGrid.SIZE);
				grid[x][y].addMouseListener(gma);
				gridPanel.add(grid[x][y],null);
			}
		}
		gpd = new Dimension(yBound*MineGrid.SIZE+6, xBound*MineGrid.SIZE+6);
	}//end of set grid panel
	
	//��ʼ�����׵ı����������õ��׵����λ��
	private void resetGridPanel() {
		
		leftCount = 0;
		int x,y,i,j;
		boolean temp;
		//�������������������������Ӧ�ķ���ȫ������Ϊ���׵�
		//ͬʱ��ÿ�����׸��ӵı���ȫ���ı�һ��
		for(x = 0; x < xBound; x++) {
			for(y =0; y < yBound; y++) {
				grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(9));
				grid[x][y].setStatus(MineGrid.NORMAL);
				//lay mines
				if(leftCount < mineCount) {
					mode[x][y] = true;
					leftCount++;
				} else {
					mode[x][y] = false;
				}
			}
		}
		//������λ���������һ��
		showCount = leftCount;
		for(x = 0; x < xBound; x++) {
			for(y =0; y < yBound; y++) {
				if(showCount==0) break;
				i = (int)(Math.random()*xBound);
				j = (int)(Math.random()*yBound);
				temp = mode[x][y];
				mode[x][y] = mode[i][j];
				mode[i][j] = temp;
				showCount--;
			}
		}
	}
	
	//���ø�frame�Ĳ˵���
	private void setMenuBar() {
		
        JMenuBar menuBar = new JMenuBar();
        //����һ������ָ����ɫ�Ϳ�ȵ��߱߿�
        menuBar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
        JMenu gameMenu = new JMenu("��Ϸ(G)");
        JMenu helpMenu = new JMenu("����(H)");
        //setMnemonic  ���ü������Ƿ� ALT+���Ƿ�ʱ��Ч
        gameMenu.setMnemonic('G');
        helpMenu.setMnemonic('H');
        
        startItem = new JMenuItem("����(N)");
        //������ϼ�������ֱ�ӵ��ò˵���Ĳ�����������������ʾ�˵��Ĳ�νṹ
		startItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        logItem = new JMenuItem("ɨ��Ӣ�۰�(T)...");
        //���Ա�ѡ����ȡ��ѡ���Ĳ˵���
        markCheckItem = new JCheckBoxMenuItem("���(?)(M)");
        exitItem = new JMenuItem("�˳�(X)");
        aboutItem = new JMenuItem("����ɨ��(A)...");
        startItem.setMnemonic('N');
        exitItem.setMnemonic('X');
        aboutItem.setMnemonic('A');
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
        logItem.setMnemonic('T');
        markCheckItem.setMnemonic('M');
        //���ð�ť��״̬
        markCheckItem.setSelected(markCheck);
        
        gameMenu.add(startItem);
        gameMenu.addSeparator();
        
        //radio group
        levelItem = new JRadioButtonMenuItem[4];
        ButtonGroup levelGroup = new ButtonGroup();
        levelItem[0] = new JRadioButtonMenuItem("����(B)");
        levelItem[1] = new JRadioButtonMenuItem("�м�(I)");
        levelItem[2] = new JRadioButtonMenuItem("�߼�(E)");
        levelItem[3] = new JRadioButtonMenuItem("�Զ���(C)...");
        levelItem[0].setMnemonic('B');
        levelItem[1].setMnemonic('I');
        levelItem[2].setMnemonic('E');
        levelItem[3].setMnemonic('C');
        for(int i=0;i<4;i++) {
	        levelGroup.add(levelItem[i]);
	        levelItem[i].addActionListener(this);
			gameMenu.add(levelItem[i]);
        }
        levelItem[currentLevel].setSelected(true);
        
        gameMenu.addSeparator();
        gameMenu.add(markCheckItem);
        gameMenu.addSeparator();
        gameMenu.add(logItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        
        helpMenu.add(aboutItem);
        
        startItem.addActionListener(this);
        markCheckItem.addActionListener(this);
        logItem.addActionListener(this);
        exitItem.addActionListener(this);
        aboutItem.addActionListener(this);
        
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        //����menuBarΪ��ǰ�Ĳ˵���
        setJMenuBar(menuBar);
	}
	
	//�������ڶԻ��򣬽���ǰ�ȼ���Ϣ���ݸ�����з�װ
	private void showAboutDialog() {
		String readme = "";
		File file = new File("readme.txt");
		if(file.exists()) {
			try {
				//�����ݷ�������
				BufferedReader input = new BufferedReader(new FileReader(file));
				//��Ϊ���壬������ȫ����ŵ�buffer��
				StringBuffer buffer = new StringBuffer();
				String text;
				while((text = input.readLine())!=null)
					buffer.append(text+"\n");
				input.close();
				//��������������string��ʽ���
				readme = buffer.toString();
			} catch(IOException ioException) {}
		}
		AboutDialog dialog = new AboutDialog(this, "ɨ��",readme,
		ImageFactory.getInstance().getImageicon(14),
		ImageFactory.getInstance().getImageicon(16));
		dialog = null;
	}
	
	//ʵ�ֶԲ˵����ĸ���ѡ��ļ���
	public void actionPerformed(ActionEvent ae) {
		
		//�����˵����ĸ���ѡ��
		if(ae.getSource()==startItem) {
			restartGame();
		} else if(ae.getSource()==markCheckItem) {
			markCheck = markCheckItem.isSelected();
		} else if(ae.getSource()==logItem) {
			//show heros
			LogDialog dialog = new LogDialog(this, log);
			dialog = null;
		} else if(ae.getSource()==exitItem) {
			writeLog();
			System.exit(0);
		} else if(ae.getSource()==aboutItem) {
			showAboutDialog();
		} else {
			//��ѡѡ��
			int x;
			for(x = 0; x < 3; x++) {
				if(ae.getSource()==levelItem[x]) break;
			}
			if(x < 3) {
				if(currentLevel!=x) {
					currentLevel = x;
					levelInfo = LevelInfo.DEFAULT_LEVEL[currentLevel];
					resetPane();
				}
			} else {
				//�����Լ�����Ҫ�ĳ�������������������Ų����
				LevelInfo newLevel = CustomDialog.getUserLevel(this, levelInfo);
				if(newLevel!=null) {
					currentLevel = x;
					levelInfo = newLevel;
					resetPane();
				}
				levelItem[currentLevel].setSelected(true);
			}
		}
	}
	
	//����״̬���Ͳ˵����������
	private void resetPane() {
		
		Container container = getContentPane();
		container.setLayout(null);
		//�Ӵ��������Ƴ��������
		container.removeAll();
		container.setBackground(Color.LIGHT_GRAY);
		
		//���������
		setGridPanel();
		
		//����Ϊ�߽�Ĳ���
		JPanel tempPanel = new JPanel(new BorderLayout());
		//������״̬��֮��ķָ���
		tempPanel.setBounds(margin, margin, gpd.width, spd.height);
		tempPanel.add(statusPanel,BorderLayout.CENTER);
		
		container.add(tempPanel,null);
		
		tempPanel = new JPanel(new BorderLayout());
		tempPanel.setBounds(margin,margin*2+spd.height,gpd.width,gpd.height);
		tempPanel.add(gridPanel,BorderLayout.CENTER);
		tempPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		container.add(tempPanel,null);
		
		//��ɨ�׳�������ʾ����Ļ�����м�
		int X = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (gpd.width+3*margin-1)) / 2;
		int Y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (gpd.height+spd.height+4*margin+titleh)) / 2;

		setLocation(X, Y);
		setSize(gpd.width+3*margin-1, gpd.height+spd.height+4*margin+titleh);
		show();
		restartGame();
	}
	
	//��ʼ����Ϸ
	private void restartGame() {
		timepassed = 0;
		timeThread = null;
		firstClick = true;
		resetGridPanel();
		resetStatusPanel();
	}
	
	//�������ĵ���ı���ӵ�״̬��ʣ����Լ��϶�������
	private void labelMine(MineGrid g) {
	  //�鿴�Ƿ��� �ʺ� ���
		if(markCheck) {
			//being labeled then to marked
			if(g.isLabeled()) {
				g.setMarked(true);
				g.setStatus(MineGrid.NORMAL);
				g.setIcon(ImageFactory.getInstance().getImageicon(13));
				leftCount++;
			} else {
				//��ǹ����ֵ��������
				if(g.isMarked()) {
					g.setMarked(false);
					g.setIcon(ImageFactory.getInstance().getImageicon(9));
				} 
				//��ǹ�����Ϊ�����
				else {
					g.setIcon(ImageFactory.getInstance().getImageicon(10));
					g.setStatus(MineGrid.LABELED);
					leftCount--;
				}
			}
		} else {
			//being labeled
			if(g.isLabeled()) {
				g.setIcon(ImageFactory.getInstance().getImageicon(9));
				g.setStatus(MineGrid.NORMAL);
				leftCount++;
			} else {
				//not being labeled
				g.setIcon(ImageFactory.getInstance().getImageicon(10));
				g.setStatus(MineGrid.LABELED);
				leftCount--;
			}
		}
		//upgrade mineLabel
		mineLabel.setIcon(new ImageIcon(led.getLedImage(leftCount,3)));
	}

	//�����������Ҽ���������
	private void clickGrid(int x, int y, int cl) {
		//���еĸ�����Χ�ĵ�����
		int count=0;
		//��Χ�Ѿ��򿪵ĸ��ӵ���Ŀ
		int lcount=0;
		
		//���������еĸ��ӵ�״̬��=���״̬
		grid[x][y].setStatus(MineGrid.CLICKED);
		
		//������е��ǵ�������ʾ���ף���������Ϸ		
		if(mode[x][y]) {						
			grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(12));
			endGame(0);
			return;
		}
		//���ǵ���
		//������еĸ����Ա߰˸�λ�õĵ��׸���
		for(int i=grid[x][y].xlow;i<=grid[x][y].xhigh;i++) {
			for(int j=grid[x][y].ylow;j<=grid[x][y].yhigh;j++) {
				if(mode[i][j])
					count++;
				if(grid[i][j].isLabeled())
					lcount++;
			}
		}
		
		//�������
		if(cl==1) {
			//���ݵ��к���Աߵĵ������ļ��㣬���ĵ�ǰ���ӵ�ͼƬ
			grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(count));
			//��ʾ�ĸ�������1
			showCount++;
			//�ж��Ƿ������Ϸ
			if( showCount == xBound*yBound - mineCount) {
				endGame(1);
				return;
			}
		}
		//������+��Χ����Ϊ0  ���Ҽ����+����=�򿪸�����
		if((cl==1&&count==0)||cl==2&&count==lcount) {
			for(int i=grid[x][y].xlow;i<=grid[x][y].xhigh;i++) {
				for(int j=grid[x][y].ylow;j<=grid[x][y].yhigh;j++) {
					if(i==x&&j==y) continue;
					else if(grid[i][j].isNormal())
						//��ѭ���ķ�������Χ���Դ򿪵ĸ���
						clickGrid(i,j,1);
				}
			}
		}
	}

	//������Ϸ�����ж���Ӯ���䣬����Ӯ��ʱ���Ƿ��д��Ƽ�¼
	private void endGame(int status) {
		//ֹͣ����ʱ��
		timeThread=null;
		
		//ʤ��
		if(status==1) {
			//����״̬��ť��ͼƬ
			statusButton.setIcon(ImageFactory.getInstance().getImageicon(19));
			//��ʣ��������ĸ��ӵ���Ϊ������ʾΪ��־Ϊ���ĵ�״̬
			for(int x = 0; x < xBound; x++) {
				for(int y = 0; y < yBound; y++) {
					//show mines not labeled
					if(mode[x][y]&&grid[x][y].isNormal())
						grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(10));
					grid[x][y].setStatus(MineGrid.CLICKED);
				}
			}
			//ʣ�����������Ϊ0
			leftCount = 0;
			mineLabel.setIcon(new ImageIcon(led.getLedImage(0,3)));
			//show user name input
			if(currentLevel<3&&timepassed<log[currentLevel].getRecord()) {
				log[currentLevel].setRecord(timepassed);
				log[currentLevel].setUserName(
					UserDialog.showInputNameDialog(
						this,currentLevel,log[currentLevel].getUserName()));
				//���û��ְ�Ի���
				LogDialog dialog = new LogDialog(this, log);
			}
		//ʧ��
		} else {
			statusButton.setIcon(ImageFactory.getInstance().getImageicon(20));
			for(int x = 0; x < xBound; x++) {
				for(int y = 0; y < yBound; y++) {
					//��������ȫ����ʾ����
					if(mode[x][y]&&grid[x][y].isNormal())
						grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(11));
					//�²����������������Ĳ²�ĵط�
					else if(!mode[x][y]&&grid[x][y].isLabeled())
						grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(15));
					//������״̬Ϊ���
					grid[x][y].setStatus(MineGrid.CLICKED);
				}
			}
		}
	}
	
	//���������״̬ѡ���ϵĵ�����뿪�ĸ�������Ķ�Ӧ����
	private class StatusMouseAdapter extends MouseAdapter {
		
		private boolean mouseIn;
		private boolean mouseDown;
		private Icon icon;
		
		public StatusMouseAdapter() {
			super();
		}
		
		//������У��ı�ͼƬ
		public void mouseEntered(MouseEvent me) {
			mouseIn = true;
			if(mouseDown) {
				statusButton.setBorder(BorderFactory.createLoweredBevelBorder());
				icon = statusButton.getIcon();
				statusButton.setIcon(ImageFactory.getInstance().getImageicon(18));
			}
		}
		
		public void mousePressed(MouseEvent me) {
			mouseDown = true;
			statusButton.setBorder(BorderFactory.createLoweredBevelBorder());
			icon = statusButton.getIcon();
			statusButton.setIcon(ImageFactory.getInstance().getImageicon(18));
		}
		
//		������̧����һ�����״̬��ť���÷�Χ�ڣ���ʾ��ɲ���
		public void mouseReleased(MouseEvent me) {
			mouseDown = false;
			statusButton.setIcon(icon);
			statusButton.setBorder(BorderFactory.createRaisedBevelBorder());
			if(mouseIn)	restartGame();
		}
		
		public void mouseExited(MouseEvent me) {
			mouseIn = false;
			if(mouseDown) {
				statusButton.setIcon(icon);
				statusButton.setBorder(BorderFactory.createRaisedBevelBorder());
			}
		}
	}
	
	//��궯����Ӧ
	private class GridMouseAdapter extends MouseAdapter {
		
		private MineGrid current;
		private boolean leftDown;
		private boolean rightDown;
		private boolean middle;
		
		public GridMouseAdapter() {
			super();
		}
		
		public void mousePressed(MouseEvent me) {
			current = (MineGrid)me.getSource();
			//as soon as right button down, label happen
			//when not released, wait for next event
			if(me.getButton()==3) {
				rightDown = true;
				if(!current.isClicked()&&!leftDown) labelMine(current);
			}else if(me.getButton()==2) {
				rightDown = true;
				leftDown = true;
				middle = true;
			}else {
				//click and double click not happen until release button
				leftDown = true;
				if(current.isNormal())
					statusButton.setIcon(ImageFactory.getInstance().getImageicon(18));
				pressGrid(current);
			}
			if(rightDown&&leftDown) {
				//double
				pressAround(current);
			}
		}
		
		public void mouseEntered(MouseEvent me) {
			current = (MineGrid)me.getSource();
			if(leftDown&&rightDown) {
				pressAround(current);
			} else if(leftDown) {
				pressGrid(current);
			}
		}
		
		public void mouseReleased(MouseEvent me) {
			if(current.isNormal())
				statusButton.setIcon(ImageFactory.getInstance().getImageicon(17));
			int x = current.getXpos();
			int y = current.getYpos();
			if(leftDown) {
				leftDown = false;
				if(firstClick) {
					timeThread = new TimeThread();
					timeThread.start();
					firstClick = false;
					//changeMine
					if(mode[x][y]) {
						int i,j;
						do {
							i = (int)(Math.random()*xBound);
							j = (int)(Math.random()*yBound);
						} while(mode[i][j]);
						mode[x][y] = false;
						mode[i][j] = true;
					}
				}
				if(rightDown) {
					releaseAround(current);
					rightDown = false;
					if(middle) {
						middle = false;
					}
					if(current.isClicked()) clickGrid(x,y,2);
				} else {
					if(current.isNormal()) clickGrid(x,y,1);
				}
			} else {
				rightDown = false;
			}
		}
		
		public void mouseExited(MouseEvent me) {
			current = (MineGrid)me.getSource();
			if(leftDown&&rightDown) {
				releaseAround(current);
			} else if(leftDown) {
				releaseGrid(current);
			}
		}

		private void pressGrid(MineGrid g) {
			if(!g.isNormal()) return;
			g.setIcon(ImageFactory.getInstance().getImageicon(0));
		}
		
		private void releaseGrid(MineGrid g) {
			if(!g.isNormal()) return;
			g.setIcon(ImageFactory.getInstance().getImageicon(9));
		}
		
		private void pressAround(MineGrid g) {
			
			int x = g.getXpos();
			int y = g.getYpos();
			for(int i=grid[x][y].xlow;i<=grid[x][y].xhigh;i++) {
				for(int j=grid[x][y].ylow;j<=grid[x][y].yhigh;j++) {
					pressGrid(grid[i][j]);
				}
			}
		}
		
		private void releaseAround(MineGrid g) {
			
			int x = g.getXpos();
			int y = g.getYpos();
			for(int i=grid[x][y].xlow;i<=grid[x][y].xhigh;i++) {
				for(int j=grid[x][y].ylow;j<=grid[x][y].yhigh;j++) {
					releaseGrid(grid[i][j]);
				}
			}
		}
	}
	
	//ʱ���߳�
	private class TimeThread extends Thread	{
		
		public TimeThread() {}
		
		public void run() {
			
			final Thread currentThread = Thread.currentThread();
			
			while(timepassed<1000&&currentThread==timeThread) {
				//Swing ʵ�÷����ļ���
				//���� doRun.run() �� AWT �¼�ָ���߳����첽ִ��
				SwingUtilities.invokeLater(
					//inner class Runnable
					new Runnable() {
						public void run() {
							timeLabel.setIcon(new ImageIcon(led.getLedImage(timepassed,3)));
						}
					}
				);
				try	{
					//�����ʼ������999�����ʼ��ʱ
					Thread.sleep(999);
				} catch(InterruptedException i) {
					System.err.println("sleep interrupted");
				}
				timepassed++;
			}
		}//end of method run
	}
}