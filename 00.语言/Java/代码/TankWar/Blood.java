import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Blood {
	private int x, y, w, h;
	int step = 0;

	private boolean live = true;
	private int[][] pos = {
			{500,500},{515,535},{550,550},{515,565},{500,600},{485,565},{450,550},{475,535}
	};
	
	
	public Blood() {
		 w = h = 10;
	}
	
	public void draw(Graphics g) {
		if(this.isLive()) {
			Color c = g.getColor();
			g.setColor(Color.BLUE);
			g.fillOval(x, y, w, h);
			g.setColor(c);
			
			move();
		}
	}
	
	private void move() {
		step ++;
	    if(step == pos.length){
	    	step = 0;
	    }
	    
	    x = pos[step][0];
	    y = pos[step][1];
	}

	public Rectangle getRec() {
		return new Rectangle(x, y, w, h);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	
}
