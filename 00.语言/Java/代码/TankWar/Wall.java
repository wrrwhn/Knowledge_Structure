import java.awt.*;

/**
 * ǽ����
 * @author Old_yao
 *
 */
public class Wall {
	private int x, y, width, height;
	TankClient tc;
	private boolean hard = true;
	private boolean live = true;
	
	/**
	 * ǽ����Ĺ��캯��
	 * @param x ��x�᷽���ϵ�λ��
	 * @param y ��y�᷽���ϵ�λ��
	 * @param width ǽ�ڵĿ��
	 * @param height ǽ�ڵĸ߶�
	 * @param tc ��TankClient������
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
	 * ��ǽ�ڻ�����
	 * @param g ��ǰ�Ļ���
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
	 * ���ǽ�ڵĴ�С
	 * @return ���ص�ǰǽ�ڲ��������ɵľ���
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
