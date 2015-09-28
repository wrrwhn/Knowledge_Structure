import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.org.apache.xerces.internal.impl.PropertyManager;

//thread.suspend(); thread.resume();

/**
 *坦克游戏的主界面 
 * @author Old_yao
 *
 */
public class TankClient extends Frame {
	/**
	 * 游戏界面的宽度
	 */
	public static final int Game_Width=800;
	/**
	 * 游戏界面的高度
	 */
	public static final int Game_Height=600;
	
	Image offScreanImage=null;
	
	/**
	 * 好坦克击中坏坦克的数目
	 */
	public int num = 0;
	
	/**
	 * 随机数发生器
	 */
	Random r = new Random();
	
	/**
	 * 自己所能控制的坦克
	 */
	Tank mytank = new Tank(50, 50, true, Direction.STOP, this);
	
	/**
	 * 爆炸对象的集合
	 * 子弹对象的集合
	 * 坏坦克对象的集合
	 * 墙壁1
	 * 墙壁2
	 * 补血块
	 */
	List<Explode> explodes = new ArrayList<Explode>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	Wall w1 = new Wall(300, 200, 50, 250, this);
	Wall w2 = new Wall(400, 200, 500, 50, this);
	List<Wall> walls = new ArrayList<Wall>();
	Blood b = new Blood();
	
	private boolean stop = false;
	
	Thread thread = null;
	
	private int reliveTime = 1;
	
	//PropertyTc ptc = new PropertyTc();
	
	/**
	 * 将坦克-子弹-爆炸-血块-墙壁一一画出
	 * 并在坏坦克数目为0零随机添加坏坦克进来
	 */
	public void paint(Graphics g) {    //重载,画圆   自动调用
		g.drawString("Missile counter:"+missiles.size(), 10, 50);
		g.drawString("Explode counter:"+explodes.size(), 120, 50);
		g.drawString("Tanks counter:"+tanks.size(), 230, 50);
		g.drawString("Good tank's life:"+ mytank.getLife(), 340, 50);
		g.drawString("The Level :"+ num/10, 470, 50);
		
		b.draw(g);
		
		for( int i = 0; i < walls.size(); i++) {
			Color c = g.getColor();
			g.setColor(Color.darkGray);
			Wall w = walls.get(i);
			w.draw(g);
			g.setColor(c);
		}
		
		for( int i = 0; i < missiles.size(); i++){
			Color c = g.getColor();
			g.setColor(Color.ORANGE);
			w1.draw(g);
			w2.draw(g);
			g.setColor(c);
			Missile m = missiles.get(i);
			m.hitTanks(tanks);
			m.hitTank(mytank);
			m.hitWall(w1);
			m.hitWall(w2);
			m.hitWalls(walls);
			m.collidesWithMissile(missiles);		
			m.draw(g);
			
			//if(!m.isLive()) missiles.remove(m);  第一种
			//else m.draw(g);
		}
		
		for( int i = 0; i < explodes.size(); i ++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for( int i = 0; i < tanks.size(); i ++) {
			Tank k = tanks.get(i);
			k.collidesWithWall(w1);
			k.collidesWithWall(w2);
			k.collidesWithTank(tanks);
			k.collidesWithWalls(walls);
			k.draw(g);
		}
		w1.draw(g);
		w2.draw(g);
		mytank.collidesWithWall(w1);
		mytank.collidesWithWall(w2);
		mytank.collidesWithWalls(walls);
		mytank.eat(b);
		mytank.draw(g);
		
		
		if(tanks.size() == 0) {
			for( int i = 0 ; i < PropertyTc.getProperty("reTankCout") ; i ++) {
				Direction[] dirs = Direction.values();
				while(true) {
					Tank k = new Tank(r.nextInt(TankClient.Game_Width), r.nextInt(TankClient.Game_Height),false, dirs[r.nextInt(5)] ,this);
				    if(!k.getRect().intersects(w1.getRec()) && !k.getRect().intersects(w2.getRec())){
							tanks.add(k);
							break;
					    
				    }
				}
			}
		}
		if(walls.size() == 0) {
			for( int i = 0 ; i < 10; i ++) {
				while(true) {
					Wall k = new Wall(r.nextInt(TankClient.Game_Width), r.nextInt(TankClient.Game_Height), 30, 30,this);
				    if(!k.getRec().intersects(mytank.getRect()) && !k.getRec().intersects(w1.getRec()) && !k.getRec().intersects(
				    		w2.getRec())){
						walls.add(k);
						break;
				    }
				}
			}
		}
		
		drawTheEnd();
	}
	
	/**
	 * 将要画的内容先存至缓冲流中，到最后一起画出
	 */
	public void update(Graphics g){   //将原本坦克的图像刷新掉
		if(offScreanImage==null){
			offScreanImage=this.createImage(Game_Width, Game_Height);
		}
		Graphics goffScrean=offScreanImage.getGraphics();
		Color c=goffScrean.getColor();
		goffScrean.setColor(Color.GRAY);
		goffScrean.fillRect(0, 0, Game_Width, Game_Height);
		goffScrean.setColor(c);
		paint(goffScrean);
		g.drawImage(offScreanImage,0,0,null);
	}
	
	public void drawTheEnd() {
		if(3 ==reliveTime) {
			try {
				thread.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Graphics g = this.getGraphics();
			g.setFont(new Font("serial", Font.BOLD, 100));
			g.drawString("GAME OVER", 200, 200);
			
		}
	}

	/**
	 * 设置显示界面的各种参数
	 *
	 */
	public void launchFrame(){
		for( int i = 0 ; i < PropertyTc.getProperty("tankCout"); i ++) {
			tanks.add(new Tank(50 + 40*(i + 1), 500, false, Direction.D, this));
		}
		
		for( int i = 0; i < 10; i ++) {
			walls.add(new Wall(r.nextInt(TankClient.Game_Width), r.nextInt(TankClient.Game_Height), 50, 50, this, false));
		}
		
		this.setLocation(100, 100);  //窗口的右移,下移位置
		this.setSize(Game_Width,Game_Height);    //窗口大小
		this.setTitle("TankWar");    //设置标题名
		this.addWindowListener(new WindowAdapter(){  //设置关闭功能
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		this.setBackground(Color.GRAY);
		this.setResizable(false);
		
		this.addKeyListener(new KeyMonitor());
		
		/**
		 * 菜单栏
		 */
		final JMenuBar menuBar = new JMenuBar();
		this.add(menuBar, BorderLayout.NORTH);
		
		/**
		 * 菜单
		 */
		final JMenu menu = new JMenu();
		menu.setText("操作");
		menu.addSeparator();
		menuBar.add(menu);
		
		//菜单项目
		/**
		 * 启动或者结束暂停
		 */		
		final JMenuItem menuItem1 = new JMenuItem();
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!stop) {
					thread = new Thread(new PaintThread());
					thread.start();
				}
				else {
					thread.resume();
				}
			}
		});
		menuItem1.setText("开始");
		menu.add(menuItem1);
		
		/**
		 * 暂停
		 */
		final JMenuItem menuItem2 = new JMenuItem();
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.suspend();
			}
		});
		menuItem2.setText("暂停");
		menu.add(menuItem2);
		
		/**
		 * 退出程序
		 */
		final JMenuItem menuItem4 = new JMenuItem();
		menuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem4.setText("退出");
		menu.add(menuItem4);
		
		
		setVisible(true);
	}
	
	/**
	 * 刷新界面的进程
	 * @author Old_yao
	 *
	 */
	private class PaintThread implements Runnable{

		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {

		TankClient tc=new TankClient();
		tc.launchFrame();

	}

	/**
	 * 对于键盘输入的检测和相关的对策
	 * @author Old_yao
	 *
	 */
	private class KeyMonitor extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			mytank.KeyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			mytank.Keypressed(e);
		}
		
	}
	

	public synchronized void w() {
		try{
			this.wait();
		}
		catch(Exception e){}
	}
	
	public synchronized void g () {
		try{
			this.notify();
		}
		catch(Exception e){}
	}
	
}
