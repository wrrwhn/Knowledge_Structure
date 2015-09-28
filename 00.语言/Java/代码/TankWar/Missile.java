import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ӵ���
 * 
 * @author Old_yao
 * 
 */

public class Missile {
	/**
	 * �ӵ���x�����ϵ��ƶ��ٶ�
	 */
	public static final int XSPEED = 20;

	/**
	 * �ӵ���y�����ϵ��ƶ��ٶ�
	 */
	public static final int YSPEED = 20;

	/**
	 * �ӵ��ĸ߶�
	 */
	public static final int HEIGHT = 10;

	/**
	 * �ӵ��Ŀ��
	 */
	public static final int WIDTH = 10;

	/**
	 * ʹ��BIG��ʱ�ĸ߶�
	 */
	public static int HEIGHT1;

	/**
	 * ʹ��BIG��ʱ�Ŀ��
	 */
	public static int WIDTH1;

	/**
	 * �жϵ�ǰ�Ƿ�ΪBIG��
	 */
	public boolean BIG = false;

	/**
	 * �ӵ���x��yλ��
	 */
	private int x, y;

	/**
	 * �ж��Ǻõ�̹�˻��ǻ���̹����������ӵ�
	 */
	private boolean good;

	/**
	 * �ӵ��ķ���
	 */
	Direction dir;

	/**
	 * ̹���Ƿ񻹴��ı�־
	 */
	private boolean live = true;

	/**
	 * ��TankClient������
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
	 * �ӵ���Ĺ��캯��
	 * 
	 * @param x
	 *            �ӵ�����λ�õ�x��λ��
	 * @param y
	 *            �ӵ�����λ�õ�y��λ��
	 * @param dir
	 *            �ӵ��ķ���
	 */
	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	/**
	 * �ӵ���Ĺ��캯��
	 * 
	 * @param x
	 *            �ӵ�����λ�õ�x��λ��
	 * @param y
	 *            �ӵ�����λ�õ�y��λ��
	 * @param good
	 *            �ж��Ǻõ�̹�˻��ǻ���̹����������ӵ�
	 * @param dir
	 *            �ӵ��ķ���
	 * @param tc
	 *            ��TankClient������
	 */
	public Missile(int x, int y, boolean good, Direction dir, TankClient tc) {
		this(x, y, dir);
		this.good = good;
		this.tc = tc;
	}

	/**
	 * �ӵ���Ĺ��캯��
	 * 
	 * @param x
	 *            �ӵ�����λ�õ�x��λ��
	 * @param y
	 *            �ӵ�����λ�õ�y��λ��
	 * @param good
	 *            �ж��Ǻõ�̹�˻��ǻ���̹����������ӵ�
	 * @param dir
	 *            �ӵ��ķ���
	 * @param tc
	 *            ��TankClient������
	 * @param BIG
	 *            �ж��Ƿ��Ǵ��ӵ�
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
	 * ���ӵ�������
	 * 
	 * @param g
	 *            ��ǰ�Ļ���
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
	 * ���ӵ�����ʱ��ͨ������dir���ж�������Ӧ��x��y��x��y����ƶ��ĳ���
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
		 * ����ӵ��Ѿ���������Ϸ����㽫��ɾ��
		 */
		if (x < 0 || y < 0 || x > TankClient.Game_Width
				|| y > TankClient.Game_Height) {
			live = false;
			tc.missiles.remove(this);
		}
	}

	/**
	 * ��õ�ǰ�ӵ���ռ��λ��
	 * 
	 * @return ������ռ��Rectangle��ʽ����
	 */
	public Rectangle getRect() {
		if (!BIG)
			return new Rectangle(x, y, Missile.WIDTH, Missile.HEIGHT);
		else
			return new Rectangle(x, y, Missile.WIDTH1, Missile.HEIGHT1);
	}

	/**
	 * �ж��ӵ��Ƿ����̹��
	 * 
	 * @param t
	 *            ̹�˵ı���
	 * @return ����ӵ�������̹�˷���TRUE�����򷵻�FALSE
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
	 * �ж��ӵ��Ƿ�����˻���̹��
	 * 
	 * @param tanks
	 *            ��̹�˵ļ���
	 * @return ��������˷���TRUE�����򷵻�FALSE
	 */
	public boolean hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * �ж��Ƿ���ײ��ǽ����
	 * 
	 * @param w
	 *            ǽ��
	 * @return ���ײ����ǽ�ھͷ���TRUE�����򷵻�FALSE
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
	 * ��̹�˵ĺ�С�����Ե�����̹�˵��ӵ��������������Է����ӵ�
	 * 
	 * @param missiles
	 *            �ӵ��ļ���
	 * @return �����̹�˵��ӵ��кͻ�̹���ӵ���ײ����TRUE�����򷵻�FALSE
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
	 * �����Ի��Ƶ�ǽ������ʱСʱ
	 * 
	 * @param walls
	 *            ǽ�ڵļ���
	 * @return ����ɻ��Ƶ�ǽ�ڱ����Ʒ���TRUE�����򷵻�FALSE
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
