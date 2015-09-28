import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 坦克类
 * 
 * @author Old_yao
 * 
 */
public class Tank {
	/**
	 * 坦克x轴方向上的移动速度
	 */
	public static final int XSPEED = 10;
	/**
	 * 坦克y轴方向上的移动速度
	 */
	public static final int YSPEED = 10;
	
	/**
	 * 坦克的宽度
	 */
	public static final int WIDTH = 50;
	/**
	 * 坦克的高度
	 */
	public static final int HEIGHT = 50;
	
	/**
	 * 对TankClient的引用
	 */
	TankClient tc;
	
	/**
	 * 坦克的生命值
	 */
	private int life = 100;
	
	/**
	 * 坦克的出现位置的x，y轴方向上的位置
	 */
	public int x,y;
	/**
	 * 坦克上一步出现的位置
	 */
	private int oldX, oldY;
	/**
	 * 建立一个随机类对象
	 */
	private static Random r = new Random();
	
	private int step = r.nextInt(12) + 3;
	/**
	 * 血块类对象
	 */
	public BloodBar bb = new BloodBar();
		
	/**
	 * 坦克的四个方向和生存的布尔值
	 */
	private boolean BL=false, BU=false, BR=false, BD=false,  live = true;
	/**
	 * 坦克是好的还是坏的标志
	 */
	public boolean good;
		
	/**
	 * 坦克的方向
	 */
	private Direction dir = Direction.STOP;
	/**
	 * 坦克改变方向前的方向
	 */
	private Direction ptdir = Direction.D;
	
	
	/**
	 * 坦克类的构造函数
	 * 
	 * @param x
	 *            坦克出现的x轴方向上的位置
	 * @param y
	 *            坦克出现的y轴方向上的位置
	 * @param good
	 *            坦克是否是好的标志
	 */
	public Tank(int x, int y, boolean good ) {
		super();
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good ;
	}
	
	/**
	 * 坦克类的构造函数
	 * 
	 * @param x
	 *            坦克出现的x轴方向上的位置
	 * @param y
	 *            坦克出现的y轴方向上的位置
	 * @param good
	 *            坦克是否是好的标志
	 * @param dir
	 *            给坦克所设定的初始方向
	 * @param tc
	 *            对于TankClient的引用
	 */
	public Tank(int x, int y, boolean good, Direction dir, TankClient tc){
		this(x,y, good);
		this.dir = dir;
		this.tc = tc;
	}
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] img = null;
	private static Map<String, Image> image = new HashMap<String, Image>();
	
    static {
		img = new Image[] {
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1U.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1D.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1L.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1R.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1RU.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1RD.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1LU.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/1LD.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2U.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2D.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2L.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2R.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2RU.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2RD.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2LU.gif")),
		    tk.getImage(Explode.class.getClassLoader().getResource("Images/2LD.gif"))
		};
		
		image.put("1U", img[0]);
		image.put("1D", img[1]);
		image.put("1L", img[2]);
		image.put("1R", img[3]);
		image.put("1RU", img[4]);
		image.put("1RD", img[5]);
		image.put("1LU", img[6]);
		image.put("1LD", img[7]);
		image.put("2U", img[8]);
		image.put("2D", img[9]);
		image.put("2L", img[10]);
		image.put("2R", img[11]);
		image.put("2RU", img[12]);
		image.put("2RD", img[13]);
		image.put("2LU", img[14]);
		image.put("2LD", img[15]);
	};
	

	/**
	 * 将坦克画出来
	 * 
	 * @param g
	 *            当前的画笔
	 */
	public void draw(Graphics g){
		if(!this.live){
			if(!good){
				tc.tanks.remove(this);
			}
			return ;
		}
		
		if(this.good) {
			drawLine1(g);
			this.bb.draw(g);
		}
		else {
			drawLine2(g);
		}
		
		move();
	}
	
	/**
	 * 画出坦克的炮筒
	 * 
	 * @param g
	 *            当前的画笔
	 */
	public void drawLine1(Graphics g) {
		switch(ptdir){
		case L:
			g.drawImage(image.get("1L"), x, y, null);
			break;
		case LU:
			g.drawImage(image.get("1LU"), x, y, null);
			break;
		case U:
			g.drawImage(image.get("1U"), x, y, null);
			break;
		case RU:
			g.drawImage(image.get("1RU"), x, y, null);
			break;
		case R:
			g.drawImage(image.get("1R"), x, y, null);
			break;
		case RD:
			g.drawImage(image.get("1RD"), x, y, null);
			break;
		case D:
			g.drawImage(image.get("1D"), x, y, null);
			break;
		case LD:
			g.drawImage(image.get("1LD"), x, y, null);
			break;
		}
		
		if(this.dir != Direction.STOP)
			this.ptdir = this.dir;
	}
	
	public void drawLine2(Graphics g) {
		switch(ptdir){
		case L:
			g.drawImage(image.get("2L"), x, y, null);
			break;
		case LU:
			g.drawImage(image.get("2LU"), x, y, null);
			break;
		case U:
			g.drawImage(image.get("2U"), x, y, null);
			break;
		case RU:
			g.drawImage(image.get("2RU"), x, y, null);
			break;
		case R:
			g.drawImage(image.get("2R"), x, y, null);
			break;
		case RD:
			g.drawImage(image.get("2RD"), x, y, null);
			break;
		case D:
			g.drawImage(image.get("2D"), x, y, null);
			break;
		case LD:
			g.drawImage(image.get("2LD"), x, y, null);
			break;
		}
		
		if(this.dir != Direction.STOP)
			this.ptdir = this.dir;
	}
	
	/**
	 * 对于键盘按键的处理
	 * 
	 * @param e
	 */
	public void Keypressed(KeyEvent e){
		int Key = e.getKeyCode();
		switch(Key){
		case KeyEvent.VK_UP:
			BU = true;
		    break;
		case KeyEvent.VK_RIGHT:
			BR = true;
			break;
		case KeyEvent.VK_DOWN:
			BD = true;
			break;
		case KeyEvent.VK_LEFT:
			BL = true;
			break;			
		}
		locateDirection();
	}
	
	/**
	 * 发射子弹
	 * 
	 * @return Missle对象
	 */
	private Missile fire() {
		if(!live) return null;
		
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m ;
		if(good){
			m= new Missile(x, y, true, ptdir, this.tc);
		}
		else
			m = new Missile(x, y, false, ptdir, this.tc);
		tc.missiles.add(m);
		return m;
	}
	
	/**
	 * 发射有方向的子弹
	 * 
	 * @param dir
	 *            初始化方向
	 * @return 返回一个Missile的对象
	 */
	private Missile fire(Direction dir) {
		if(!live) return null;
		
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, true, dir, this.tc);
		tc.missiles.add(m);
		return m;		
	}
	
	/**
	 * 发射一个散弹，即八个方向同时发射一颗子弹
	 * 
	 */
	private void superfire() {
		Direction[] dirs = Direction.values();
		for(int i = 0; i < 8; i ++) {
			fire(dirs[i]);
		}
	}
	
	/**
	 * 发射一颗超级大的子弹
	 * 
	 * @return 返回一个Missile对象
	 */
	private Missile bigfire() {
        if(!live) return null;
		
		Missile m= new Missile(x+ Tank.WIDTH/2, y+ Tank.HEIGHT/2, true, ptdir, this.tc, true);		
		tc.missiles.add(m);
		return m;
	}

	/**
	 * 根据键盘输入确定前进的方向
	 * 
	 */
	void locateDirection(){
		if(!BU && !BR && BL && !BD) dir = Direction.L;
		if(BU && !BR && BL && !BD) dir = Direction.LU;
		if(BU && !BR && !BL && !BD) dir = Direction.U;
		if(BU && BR && !BL && !BD) dir = Direction.RU;
		if(!BU && BR && !BL && !BD) dir = Direction.R;
		if(!BU && BR && !BL && BD) dir = Direction.RD;
		if(!BU && !BR && !BL && BD) dir = Direction.D;
		if(!BU && !BR && BL && BD) dir = Direction.LD;
		if(!BU && !BR && !BL && !BD) dir = Direction.STOP;		
	}
	
	/**
	 * 根据键盘输入的数据判断后的前进方向 在x，y轴上增删相应的移动速度*时间1
	 * 
	 */
	void move(){
		
		this.oldX = x;
		this.oldY = y;
		
		switch(dir){
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= XSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;			
		}		

		if( x < 0) x = 0;
		if( y < 30 ) y = 30;
		if( x + Tank.WIDTH > TankClient.Game_Width) x = TankClient.Game_Width - Tank.WIDTH;
		if( y + Tank.HEIGHT > TankClient.Game_Height) y = TankClient.Game_Height - Tank.HEIGHT;
		
		if(!good) {
			Direction[] dirs = Direction.values();
			
			if(step == 0){
				step = r.nextInt(12) + 3;
				int NextInt = r.nextInt(dirs.length);
				dir = dirs[NextInt];
			}
			
			step --;
			
		    if(r.nextInt(40) > 35) this.fire();
		}
	}
	
	/**
	 * 返回前一时刻的位置
	 * 
	 */
	public void stay() {
		x = this.oldX;
		y = this.oldY;
	}
	
	/**
	 * 通过对键盘的监听，获得键盘的命名并采取相应的措施
	 * 
	 * @param e
	 *            键盘事件
	 */
    public void KeyReleased(KeyEvent e) {
		int Key = e.getKeyCode();
		switch(Key){
		case KeyEvent.VK_F2:
			if(!this.live) {
				this.live = true;
				this.life = 100;
			}
			break;
		case KeyEvent.VK_CONTROL:
		    fire();
		    break;
		case KeyEvent.VK_UP:
			BU = false;
		    break;
		case KeyEvent.VK_RIGHT:
			BR = false;
			break;
		case KeyEvent.VK_DOWN:
			BD = false;
			break;
		case KeyEvent.VK_LEFT:
			BL = false;
			break;
		case KeyEvent.VK_A:
			superfire();
			break;
		case KeyEvent.VK_S:
			bigfire();
			break;
		}
		locateDirection();
	}
	
    /**
	 * 获得坦克的面积所组成的矩形类对象
	 * 
	 * @return 坦克的矩形形式对象
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, Tank.WIDTH, Tank.HEIGHT);
	}
	
	/**
	 * 判断坦克是否撞到墙壁上了
	 * 
	 * @param w
	 *            墙壁
	 * @return 如果撞到墙壁上就返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithWall(Wall w) {
		if(this.live && this.getRect().intersects(w.getRec())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	/**
	 * 判断坏的坦克们是否撞到墙壁上
	 * 
	 * @param tanks
	 *            坏坦克的集合
	 * @return 如果有一个撞到墙壁上就返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithTank(java.util.List<Tank> tanks) {
		for( int i = 0; i < tanks.size(); i ++) {
			Tank t = tanks.get(i);
			if(this != t && this.getRect().intersects(t.getRect())){
				this.stay();
				t.stay();
				return true;
			}
		}
		return false;
	}

	/**
	 * 设定坦克不能融入可击破的墙壁中
	 * 
	 * @param walls
	 *            可以击破的墙壁的集合
	 * @return 如果碰撞到了返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithWalls(java.util.List<Wall> walls) {
		for( int i = 0; i < walls.size(); i ++) { 
			Wall w = walls.get(i);
			if(this.collidesWithWall(w)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	
	public boolean isGood() {
		return good;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	/**
	 * 内部类：血块类
	 * 
	 * @author Old_yao
	 * 
	 */
	public class BloodBar{
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(340, 25, 110, 10);
			g.fillRect(340, 25, 110*life/100, 10);
			g.setColor(c);
		}
	}
	
	/**
	 * 坦克吃血块补充life
	 * 
	 * @param b
	 *            血块
	 * @return 血少的时候吃了补充了life后返回TRUE，否则返回FALSE
	 */
	public boolean eat(Blood b) {
		if( this.live && b.isLive() && this.getRect().intersects(b.getRec()) ) {
			if(this.life <= 50) {
			this.life += 50;
			}
			else if(this.life <= 100) {
				this.life = 100;
			}
			b.setLive(false);
			return true;
		}
		return false;
	}
	
}
