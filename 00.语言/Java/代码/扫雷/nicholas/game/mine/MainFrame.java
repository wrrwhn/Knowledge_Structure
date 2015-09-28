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
	
	//Dimension封装单个对象中组件的宽度和高度
	private Dimension gpd;
	private Dimension spd;
	private MineGrid grid[][];
	private boolean mode[][];
	
	//页边空白 
	private final int margin = 7;
	private final int titleh = 52;
	
	private int xBound;
	private int yBound;
	private int mineCount;
	private int showCount;		//打开的格子总数
	private int leftCount;		//剩余的，自己所为判断的地雷个数
	private int timepassed;
	private boolean firstClick;
	private boolean markCheck;
	
	
	//Menu Components
	private JMenuItem startItem;
	private JMenuItem exitItem;
	private JMenuItem logItem;
	private JMenuItem aboutItem;
		//单项选择，等级
	private JRadioButtonMenuItem levelItem[];
		//含选择框，是否含有标记
	private JCheckBoxMenuItem markCheckItem;
		
	
	//Game informations
	private LevelInfo levelInfo;
	private int currentLevel;
	private LevelLog log[];
	
	private LedNumber led;
	
	private GridMouseAdapter gma;
	private StatusMouseAdapter sma;
	private TimeThread timeThread;
	
	//绘制主窗口
	public MainFrame() {
		//标题为扫雷
		super("扫雷");
		
		//默认当前等级为0
		currentLevel = 0;
		//对应等级的各种个数和雷数、记录。
		levelInfo = LevelInfo.DEFAULT_LEVEL[currentLevel];
		
		//三个等级的的数据初始化
		log = new LevelLog[3];
		for(int i=0;i<3;i++)
			log[i] = new LevelLog();
		
		//read from file
		readLog();
		
		led = new LedNumber();
		
		gma = new GridMouseAdapter();		//格子监听器
		sma = new StatusMouseAdapter();		//状态按钮的监听器
		
		//显示的三大区域
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
		
		//设置图标为 地雷
		setIconImage(ImageFactory.getInstance().getImageicon(16).getImage());
		//设置为窗口不可拖动
		setResizable(false);
	}
	
	//调用程序，创建出主窗口
	public static void main(String args[]) {
		MainFrame application = new MainFrame();
		try {
			//跟踪当前的外观及其默认设置
			//使用 LookAndFeel 对象设置当前的默认外观
			//返回实现本机系统外观（如果有一个）的 LookAndFeel 类的名称，否则返回默认的跨平台 LookAndFeel 类的名称
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
	}
	
	//打开文件，用来读取自定义等级的记录信息
	private void readLog() {
		try {
			//File 文件和目录路径名的抽象表示形式
			File logFile = new File("mine.log");
			if(!logFile.exists()) return;
			//用于接受对应文件内的图片内容
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(logFile));
			for(int i=0;i<3;i++) {
				//从 ObjectInputStream 读取对象
				log[i] = (LevelLog)ois.readObject();
			}
			//以 boolean 形式读取OIS流
			markCheck = ois.readBoolean();
			currentLevel = ois.readInt();
			//自定义的情况
			//否则则根据类中的数据进行初始化
			if(currentLevel==3) {
				levelInfo = (LevelInfo)ois.readObject();
			} else {
				levelInfo = LevelInfo.DEFAULT_LEVEL[currentLevel];
			}
			ois.close();
		} catch (Exception e) {System.out.println("read fail");}
	}
	
	//打开文件，存储自定义等级的记录数据
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
		
	//显示剩余雷数、时间和状态控制标志的标签
	private void setStatusPanel() {
		
		JPanel temp;
		statusPanel = new JPanel(new BorderLayout());
		
		//剩余雷数标签
		mineLabel = new JLabel();
		//创建一个具有凹入斜面边缘的边框，将组件当前背景色的较亮的色度用于突出显示，较暗的色度用于阴影。
		mineLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		//FlowLayout  创建一个新的流布局管理器，具有指定的对齐方式以及指定的水平和垂直间隙。
		temp = new JPanel(new FlowLayout(1,4,4));
		temp.add(mineLabel);
		temp.setBackground(Color.LIGHT_GRAY);
		statusPanel.add(temp,BorderLayout.WEST);
		
	  //时间标签
		timeLabel = new JLabel();
        timeLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		temp = new JPanel(new FlowLayout(1,4,4));
		temp.add(timeLabel);
		temp.setBackground(Color.LIGHT_GRAY);
		statusPanel.add(temp,BorderLayout.EAST);
        
	  //状态按钮标签
        statusButton = new JLabel();
		statusButton.setBorder(BorderFactory.createRaisedBevelBorder());
		statusButton.addMouseListener(sma);
		temp = new JPanel(new FlowLayout(1,0,4));
		temp.setBackground(Color.LIGHT_GRAY);
		temp.add(statusButton);
		statusPanel.add(temp,BorderLayout.CENTER);
		
		//设置该标签的长和宽   但在后面调节中是可变的
		statusPanel.setSize(10,37);
		statusPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		//取得当前statusPanel的精确的长和宽
		spd = statusPanel.getSize();
	}
	
	//初始化状态栏的两个标签和一个按钮
	private void resetStatusPanel() {
		mineLabel.setIcon(new ImageIcon(led.getLedImage(leftCount,3)));
		timeLabel.setIcon(new ImageIcon(led.getLedImage(timepassed,3)));
		statusButton.setIcon(ImageFactory.getInstance().getImageicon(17));
	}

	//排列雷区和布置雷
	private void setGridPanel() {
		//分别存放当前所存放的雷数所堆积的长和高
		xBound = levelInfo.getXBound();
		yBound = levelInfo.getYBound();
		mineCount = levelInfo.getMineCount();
		MineGrid.xBound = this.xBound;
		MineGrid.yBound = this.yBound;
		grid = new MineGrid[xBound][yBound];
		//判断是否点击
		mode = new boolean[xBound][yBound];
		
		gridPanel = new JPanel();
		gridPanel.setBackground(Color.GRAY);
		//initialize grid panel
		gridPanel.setLayout(null);
		
		//逐一在面板中添加地雷
		//xBound 长		yBound  宽
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
	
	//初始化地雷的背景，并设置地雷的随机位置
	private void resetGridPanel() {
		
		leftCount = 0;
		int x,y,i,j;
		boolean temp;
		//首先设置最上面的所有雷数对应的方块全部设置为有雷的
		//同时将每个地雷格子的背景全部改变一下
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
		//将地雷位置随机调动一下
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
	
	//设置该frame的菜单栏
	private void setMenuBar() {
		
        JMenuBar menuBar = new JMenuBar();
        //创建一个具有指定颜色和宽度的线边框
        menuBar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
        JMenu gameMenu = new JMenu("游戏(G)");
        JMenu helpMenu = new JMenu("帮助(H)");
        //setMnemonic  设置键盘助记符 ALT+助记符时有效
        gameMenu.setMnemonic('G');
        helpMenu.setMnemonic('H');
        
        startItem = new JMenuItem("开局(N)");
        //设置组合键，它能直接调用菜单项的操作侦听器而不必显示菜单的层次结构
		startItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        logItem = new JMenuItem("扫雷英雄榜(T)...");
        //可以被选定或取消选定的菜单项
        markCheckItem = new JCheckBoxMenuItem("标记(?)(M)");
        exitItem = new JMenuItem("退出(X)");
        aboutItem = new JMenuItem("关于扫雷(A)...");
        startItem.setMnemonic('N');
        exitItem.setMnemonic('X');
        aboutItem.setMnemonic('A');
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
        logItem.setMnemonic('T');
        markCheckItem.setMnemonic('M');
        //设置按钮的状态
        markCheckItem.setSelected(markCheck);
        
        gameMenu.add(startItem);
        gameMenu.addSeparator();
        
        //radio group
        levelItem = new JRadioButtonMenuItem[4];
        ButtonGroup levelGroup = new ButtonGroup();
        levelItem[0] = new JRadioButtonMenuItem("初级(B)");
        levelItem[1] = new JRadioButtonMenuItem("中级(I)");
        levelItem[2] = new JRadioButtonMenuItem("高级(E)");
        levelItem[3] = new JRadioButtonMenuItem("自定义(C)...");
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
        //设置menuBar为当前的菜单栏
        setJMenuBar(menuBar);
	}
	
	//创建关于对话框，将当前等级信息传递给类进行封装
	private void showAboutDialog() {
		String readme = "";
		File file = new File("readme.txt");
		if(file.exists()) {
			try {
				//将数据分行输入
				BufferedReader input = new BufferedReader(new FileReader(file));
				//作为缓冲，将数据全部存放到buffer中
				StringBuffer buffer = new StringBuffer();
				String text;
				while((text = input.readLine())!=null)
					buffer.append(text+"\n");
				input.close();
				//将缓冲区内容以string形式输出
				readme = buffer.toString();
			} catch(IOException ioException) {}
		}
		AboutDialog dialog = new AboutDialog(this, "扫雷",readme,
		ImageFactory.getInstance().getImageicon(14),
		ImageFactory.getInstance().getImageicon(16));
		dialog = null;
	}
	
	//实现对菜单栏的各个选项的监听
	public void actionPerformed(ActionEvent ae) {
		
		//监听菜单栏的各种选项
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
			//单选选项
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
				//输入自己所需要的长宽和雷数，并且重新排布面板
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
	
	//排列状态栏和菜单栏外的区域
	private void resetPane() {
		
		Container container = getContentPane();
		container.setLayout(null);
		//从此容器中移除所有组件
		container.removeAll();
		container.setBackground(Color.LIGHT_GRAY);
		
		//设置类详解
		setGridPanel();
		
		//设置为边界的布局
		JPanel tempPanel = new JPanel(new BorderLayout());
		//雷区与状态栏之间的分隔栏
		tempPanel.setBounds(margin, margin, gpd.width, spd.height);
		tempPanel.add(statusPanel,BorderLayout.CENTER);
		
		container.add(tempPanel,null);
		
		tempPanel = new JPanel(new BorderLayout());
		tempPanel.setBounds(margin,margin*2+spd.height,gpd.width,gpd.height);
		tempPanel.add(gridPanel,BorderLayout.CENTER);
		tempPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		container.add(tempPanel,null);
		
		//让扫雷程序处在显示器屏幕的最中间
		int X = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (gpd.width+3*margin-1)) / 2;
		int Y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (gpd.height+spd.height+4*margin+titleh)) / 2;

		setLocation(X, Y);
		setSize(gpd.width+3*margin-1, gpd.height+spd.height+4*margin+titleh);
		show();
		restartGame();
	}
	
	//初始化游戏
	private void restartGame() {
		timepassed = 0;
		timeThread = null;
		firstClick = true;
		resetGridPanel();
		resetStatusPanel();
	}
	
	//根据鼠标的点击改变格子的状态和剩余的自己认定的雷数
	private void labelMine(MineGrid g) {
	  //查看是否有 问号 标记
		if(markCheck) {
			//being labeled then to marked
			if(g.isLabeled()) {
				g.setMarked(true);
				g.setStatus(MineGrid.NORMAL);
				g.setIcon(ImageFactory.getInstance().getImageicon(13));
				leftCount++;
			} else {
				//标记过后又点击成正常
				if(g.isMarked()) {
					g.setMarked(false);
					g.setIcon(ImageFactory.getInstance().getImageicon(9));
				} 
				//标记过后变成为有旗的
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

	//左键点击和左右键点击的情况
	private void clickGrid(int x, int y, int cl) {
		//点中的格子周围的地雷数
		int count=0;
		//周围已经打开的格子的数目
		int lcount=0;
		
		//更改鼠标点中的格子的状态：=点击状态
		grid[x][y].setStatus(MineGrid.CLICKED);
		
		//如果点中的是地雷则显示地雷，并结束游戏		
		if(mode[x][y]) {						
			grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(12));
			endGame(0);
			return;
		}
		//不是地雷
		//计算点中的格子旁边八个位置的地雷个数
		for(int i=grid[x][y].xlow;i<=grid[x][y].xhigh;i++) {
			for(int j=grid[x][y].ylow;j<=grid[x][y].yhigh;j++) {
				if(mode[i][j])
					count++;
				if(grid[i][j].isLabeled())
					lcount++;
			}
		}
		
		//左键点中
		if(cl==1) {
			//根据点中后对旁边的地雷数的计算，更改当前格子的图片
			grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(count));
			//显示的格子增加1
			showCount++;
			//判断是否结束游戏
			if( showCount == xBound*yBound - mineCount) {
				endGame(1);
				return;
			}
		}
		//左键点击+周围雷数为0  左右键点击+雷数=打开格子数
		if((cl==1&&count==0)||cl==2&&count==lcount) {
			for(int i=grid[x][y].xlow;i<=grid[x][y].xhigh;i++) {
				for(int j=grid[x][y].ylow;j<=grid[x][y].yhigh;j++) {
					if(i==x&&j==y) continue;
					else if(grid[i][j].isNormal())
						//以循环的方法打开周围可以打开的格子
						clickGrid(i,j,1);
				}
			}
		}
	}

	//结束游戏，并判断是赢是输，和在赢的时候是否有打破记录
	private void endGame(int status) {
		//停止计算时间
		timeThread=null;
		
		//胜利
		if(status==1) {
			//更改状态按钮的图片
			statusButton.setIcon(ImageFactory.getInstance().getImageicon(19));
			//将剩余的正常的格子调整为地雷显示为标志为旗帜的状态
			for(int x = 0; x < xBound; x++) {
				for(int y = 0; y < yBound; y++) {
					//show mines not labeled
					if(mode[x][y]&&grid[x][y].isNormal())
						grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(10));
					grid[x][y].setStatus(MineGrid.CLICKED);
				}
			}
			//剩余的雷数更改为0
			leftCount = 0;
			mineLabel.setIcon(new ImageIcon(led.getLedImage(0,3)));
			//show user name input
			if(currentLevel<3&&timepassed<log[currentLevel].getRecord()) {
				log[currentLevel].setRecord(timepassed);
				log[currentLevel].setUserName(
					UserDialog.showInputNameDialog(
						this,currentLevel,log[currentLevel].getUserName()));
				//调用积分榜对话框
				LogDialog dialog = new LogDialog(this, log);
			}
		//失败
		} else {
			statusButton.setIcon(ImageFactory.getInstance().getImageicon(20));
			for(int x = 0; x < xBound; x++) {
				for(int y = 0; y < yBound; y++) {
					//将其他雷全部显示出来
					if(mode[x][y]&&grid[x][y].isNormal())
						grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(11));
					//猜测错误的情况，将错误的猜测的地方
					else if(!mode[x][y]&&grid[x][y].isLabeled())
						grid[x][y].setIcon(ImageFactory.getInstance().getImageicon(15));
					//更改其状态为点击
					grid[x][y].setStatus(MineGrid.CLICKED);
				}
			}
		}
	}
	
	//设置鼠标在状态选项上的点击和离开的各种情况的对应策略
	private class StatusMouseAdapter extends MouseAdapter {
		
		private boolean mouseIn;
		private boolean mouseDown;
		private Icon icon;
		
		public StatusMouseAdapter() {
			super();
		}
		
		//鼠标点击中，改变图片
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
		
//		当鼠标键抬起而且还是再状态按钮作用范围内，表示完成操作
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
	
	//鼠标动作相应
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
	
	//时间线程
	private class TimeThread extends Thread	{
		
		public TimeThread() {}
		
		public void run() {
			
			final Thread currentThread = Thread.currentThread();
			
			while(timepassed<1000&&currentThread==timeThread) {
				//Swing 实用方法的集合
				//导致 doRun.run() 在 AWT 事件指派线程上异步执行
				SwingUtilities.invokeLater(
					//inner class Runnable
					new Runnable() {
						public void run() {
							timeLabel.setIcon(new ImageIcon(led.getLedImage(timepassed,3)));
						}
					}
				);
				try	{
					//点击开始后，休眠999毫秒后开始计时
					Thread.sleep(999);
				} catch(InterruptedException i) {
					System.err.println("sleep interrupted");
				}
				timepassed++;
			}
		}//end of method run
	}
}