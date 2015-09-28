import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * 爆炸类
 * @author Old_yao
 *
 */

public class Explode {
	/**
	 * 爆炸的坐标
	 */
	int x, y;
	
	/**
	 * 爆炸的生命
	 */
	private boolean live = true;	

	private boolean init = false;
	
    /**
     * 对于TankClient的引用
     */
	TankClient tc;
	
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] img ={
		tk.getImage(Explode.class.getClassLoader().getResource("Images/0.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/1.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/2.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/3.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/4.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/5.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/6.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/7.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/8.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/9.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("Images/10.gif"))
		
	};
	
	/**
	 * 爆炸的程度
	 */
	int step = 0;
	
	
	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
		this.tc.num ++;
	}
    
	/**
	 * 按diameter画出爆炸
	 * @param g 当前画笔
	 */
	public void draw(Graphics g){
		
		if(false == init) {
				for (int i = 0; i < img.length; i++) {
					g.drawImage(img[i], -100, -100, null);
				}
			init = true;
		}
		
		if(!live) {
			tc.explodes.remove(this);
			return ;
		}
		
		if(step == img.length){
			live = false;
			step = 0;
			return;
		}
		
		g.drawImage(img[step], x, y, null);
		
		step ++;
	}

}
