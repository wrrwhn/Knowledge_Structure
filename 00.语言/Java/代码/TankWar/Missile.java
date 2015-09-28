import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子弹类
 * 
 * @author Old_yao
 * 
 */

public class Missile {
	/**
	 * 子弹在x方向上的移动速度
	 */
	public static final int XSPEED = 20;

	/**
	 * 子弹在y方向上的移动速度
	 */
	public static final int YSPEED = 20;

	/**
	 * 子弹的高度
	 */
	public static final int HEIGHT = 10;

	/**
	 * 子弹的宽度
	 */
	public static final int WIDTH = 10;

	/**
	 * 使用BIG弹时的高度
	 */
	public static int HEIGHT1;

	/**
	 * 使用BIG弹时的宽度
	 */
	public static int WIDTH1;

	/**
	 * 判断当前是否为BIG弹
	 */
	public boolean BIG = false;

	/**
	 * 子弹的x和y位置
	 */
	private int x, y;

	/**
	 * 判断是好的坦克还是坏的坦克所发射的子弹
	 */
	private boolean good;

	/**
	 * 子弹的方向
	 */
	Direction dir;

	/**
	 * 坦克是否还存活的标志
	 */
	private boolean live = true;

	/**
	 * 对TankClient的引用
	 */
	private TankClient tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] img = null;

	private static Map<String, Image> image = new HashMap<String, Image>();

	static {
		img = new Image[] {
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MU.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MD.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/ML.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MR.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MRU.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MRD.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MLU.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/MLD.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3U.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3D.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3L.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3R.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3RU.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3RD.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3LU.gif")),
				tk.getImage(Explode.class.getClassLoader().getResource(
						"Images/3LD.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource(
						"Images/big.gif")) };

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
		image.put("big", img[16]);
	};

	public boolean isLive() {
		return live;
	}

	/**
	 * 子弹类的构造函数
	 * 
	 * @param x
	 *            子弹生成位置的x轴位置
	 * @param y
	 *            子弹生成位置的y轴位置
	 * @param dir
	 *            子弹的方向
	 */
	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	/**
	 * 子弹类的构造函数
	 * 
	 * @param x
	 *            子弹生成位置的x轴位置
	 * @param y
	 *            子弹生成位置的y轴位置
	 * @param good
	 *            判断是好的坦克还是坏的坦克所发射的子弹
	 * @param dir
	 *            子弹的方向
	 * @param tc
	 *            对TankClient的引用
	 */
	public Missile(int x, int y, boolean good, Direction dir, TankClient tc) {
		this(x, y, dir);
		this.good = good;
		this.tc = tc;
	}

	/**
	 * 子弹类的构造函数
	 * 
	 * @param x
	 *            子弹生成位置的x轴位置
	 * @param y
	 *            子弹生成位置的y轴位置
	 * @param good
	 *            判断是好的坦克还是坏的坦克所发射的子弹
	 * @param dir
	 *            子弹的方向
	 * @param tc
	 *            对TankClient的引用
	 * @param BIG
	 *            判断是否是大子弹
	 */
	public Missile(int x, int y, boolean good, Direction dir, TankClient tc,
			boolean BIG) {
		this(x - Tank.WIDTH / 2, y - Tank.HEIGHT / 2, good, dir, tc);
		this.HEIGHT1 = 5 * HEIGHT;
		this.WIDTH1 = 5 * WIDTH;
		this.BIG = BIG;
	}

	public void drawLine1(Graphics g) {
		switch (dir) {
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
	}

	public void drawLineBig(Graphics g) {
		switch (dir) {
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
	}

	public void drawBig(Graphics g) {
		g.drawImage(image.get("big"), x, y, null);
	}

	/**
	 * 将子弹画出来
	 * 
	 * @param g
	 *            当前的画笔
	 */
	public void draw(Graphics g) {
		if (this.good && !BIG) {
			this.drawLine1(g);
		} else if (!this.good && !BIG) {
			this.drawLine1(g);
		} else if (BIG) {
			this.drawBig(g);
			// this.drawLineBig(g);
		}

		move();
	}

	/**
	 * 在子弹存活的时候，通过方向dir的判断来给对应的x，y轴x，y添加移动的长度
	 * 
	 */
	public void move() {
		if (!live)
			tc.missiles.remove(this);
		switch (dir) {
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

		/**
		 * 如果子弹已经超出了游戏界面便将它删除
		 */
		if (x < 0 || y < 0 || x > TankClient.Game_Width
				|| y > TankClient.Game_Height) {
			live = false;
			tc.missiles.remove(this);
		}
	}

	/**
	 * 获得当前子弹所占的位置
	 * 
	 * @return 返回所占的Rectangle形式数据
	 */
	public Rectangle getRect() {
		if (!BIG)
			return new Rectangle(x, y, Missile.WIDTH, Missile.HEIGHT);
		else
			return new Rectangle(x, y, Missile.WIDTH1, Missile.HEIGHT1);
	}

	/**
	 * 判断子弹是否击中坦克
	 * 
	 * @param t
	 *            坦克的变量
	 * @return 如果子弹击中了坦克返回TRUE，否则返回FALSE
	 */
	public boolean hitTank(Tank t) {
		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive()
				&& this.good != t.isGood()) {
			if (!BIG) {
				if (t.good) {
					if (t.getLife() > 0) {
						t.setLife(t.getLife() - 20);
						if (t.getLife() == 0) {
							t.setLive(false);
							Explode e = new Explode(x, y, tc);
							tc.explodes.add(e);
						}
					} else {
						t.setLive(false);
						Explode e = new Explode(x, y, tc);
						tc.explodes.add(e);
					}
					this.live = false;
				} else {
					this.live = false;
					t.setLive(false);
					Explode e = new Explode(x, y, tc);
					tc.explodes.add(e);
					return true;
				}
			} else {
				t.setLive(false);
				Explode e = new Explode(x, y, tc);
				tc.explodes.add(e);
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断子弹是否击中了坏的坦克
	 * 
	 * @param tanks
	 *            坏坦克的集合
	 * @return 如果击中了返回TRUE，否则返回FALSE
	 */
	public boolean hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * 判断是否有撞到墙壁上
	 * 
	 * @param w
	 *            墙壁
	 * @return 如果撞到了墙壁就返回TRUE，否则返回FALSE
	 */
	public boolean hitWall(Wall w) {
		if (this.live && this.getRect().intersects(w.getRec())) {
			if (!w.isHard()) {
				this.live = false;
				w.setLive(false);
				Explode e = new Explode(x, y, tc);
				tc.explodes.add(e);
				return true;
			} else {
				this.live = false;
				return true;
			}
		}
		return false;
	}

	/**
	 * 好坦克的好小弹可以抵消坏坦克的子弹，大弹则可以消灭对方的子弹
	 * 
	 * @param missiles
	 *            子弹的集合
	 * @return 如果好坦克的子弹有和坏坦克子弹像撞返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithMissile(java.util.List<Missile> missiles) {
		for (int i = 0; i < missiles.size(); i++) {
			Missile t = missiles.get(i);
			if (this.good && !t.good && this.getRect().intersects(t.getRect())) {
				if (this.BIG) {
					t.live = false;
					return true;
				} else {
					this.live = false;
					t.live = false;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 当可以击破的墙被击中时小时
	 * 
	 * @param walls
	 *            墙壁的集合
	 * @return 如果可击破的墙壁被击破返回TRUE，否则返回FALSE
	 */
	public boolean hitWalls(List<Wall> walls) {
		for (int i = 0; i < walls.size(); i++) {
			if (hitWall(walls.get(i))) {
				tc.walls.remove(i);
				return true;
			}
		}
		return false;
	}
}
