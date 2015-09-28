// 布置雷区

package nicholas.game.mine;

import java.awt.*;

import javax.swing.*;

public class MineGrid extends JLabel {
	
	//按钮控件点中的	
	public static final int CLICKED = 0;
	//标记的
	public static final int LABELED = 1;
	public static final int NORMAL = 2;
	public static final int SIZE = 16;
	
	public static int xBound;
	public static int yBound;
	
	private int x, y;
	private int status;
	//标记 
	private boolean mark;
	
	int xlow;
	int ylow;
	int xhigh;
	int yhigh;
	
	public MineGrid(int x, int y) {
		
		super();
		this.x = x;
		this.y = y;
		status = NORMAL;
		mark = false;
		xhigh = x;
		yhigh = y;
		xlow = x;
		ylow = y;
		//???
		if(x>0) xlow--;
		if(x<xBound-1) xhigh++;
		if(y>0) ylow--;
		if(y<yBound-1) yhigh++;
	}
	
	public void setMarked(boolean m) {
		mark = m;
	}
	
	public boolean isMarked() {
		return mark;
	}
	
	public void setStatus(int s) {
		status = s;
	}
	
	public int getXpos() {
		return x;
	}
	
	public int getYpos() {
		return y;
	}
	
	public boolean isClicked() {
		return status == CLICKED;
	}
	
	public boolean isLabeled() {
		return status == LABELED;
	}
	
	public boolean isNormal() {
		return status == NORMAL;
	}
}

/********************************************\
* status  clickable  labelable  doubleClick  *
*clicked    false      false       true      *
*labeled    false      true        false     *
* normal    true       true        false     *
\********************************************/
