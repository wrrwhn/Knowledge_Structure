import java.awt.*;

/**
 * 墙壁类
 * @author Old_yao
 *
 */
public class Wall {
	private int x, y, width, height;
	TankClient tc;
	private boolean hard = true;
	private boolean live = true;
	
	/**
	 * 墙壁类的构造函数
	 * @param x 其x轴方向上的位置
	 * @param y 其y轴方向上的位置
	 * @param width 墙壁的宽度
	 * @param height 墙壁的高度
	 * @param tc 对TankClient的引用
	 */
	public Wall(int x, int y, int width, int height, TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tc = tc;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param tc
	 * @param live
	 */
	public Wall(int x, int y, int width, int height, TankClient tc, boolean hard) {
		this(x,y,width,height,tc);
		this.hard = hard;
	}
	
	/**
	 * 将墙壁画出来
	 * @param g 当前的画笔
	 */
	public void draw(Graphics g) {
		if(live) {
			Color c = g.getColor();
			g.setColor(Color.ORANGE);
			g.fillRect(x, y, width, height);
			g.setColor(c);
		}
	}
	
	/**
	 * 获得墙壁的大小
	 * @return 返回当前墙壁参数所构成的矩形
	 */
	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);
	}

	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean hard) {
		this.hard = hard;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
}
