	//确定默认等级所对应的雷区和雷数
	//排名的记录人和时间
package nicholas.game.mine;

import java.io.Serializable;

/*
 *modifiable level
 */
public class LevelInfo implements Serializable {
	
	public static final LevelInfo DEFAULT_LEVEL[] = {
		new LevelInfo(9, 9, 10),
		new LevelInfo(16, 16, 40),
		new LevelInfo(16, 30, 99)
	};
	
	private int mineCount;
	private int xBound;
	private int yBound;
	
	//初始化雷区的大小和雷数
	public LevelInfo(int x, int y, int mc){
		
		if(x > 24) {
			xBound = 24;
		} else if(x < 9) {
			xBound = 9;
		} else {
			xBound = x;
		}
		
		if(y > 30) {
			yBound = 30;
		} else if(y < 9) {
			yBound = 9;
		} else {
			yBound = y;
		}
		
		if(mc > (xBound-1)*(yBound-1)) {
			mineCount = (xBound-1)*(yBound-1);
		} else if(mc < 10) {
			mineCount = 10;
		} else {
			mineCount = mc;
		}
	}
	
	public int getMineCount() {
		return mineCount;
	}
	
	public int getXBound() {
		return xBound;
	}
	
	public int getYBound() {
		return yBound;
	}
}

/*
 *更改所创造的最新记录的人和时间
 */
class LevelLog implements Serializable {
	
	private static final String DEFAULT_NAME = "匿名";
	private static final int DEFAULT_RECORD = 999;
	
	private int record;
	private String user;
	
	public LevelLog() {
		setDefault();
	}
	
	public void setDefault() {
		user = DEFAULT_NAME;
		record = DEFAULT_RECORD;
	}
	
	public void setRecord(int r) {
		record = r;
	}
	
	public void setUserName(String name) {
		user = name;
	}
	
	public int getRecord() {
		return record;
	}
	
	public String getUserName() {
		return user;
	}
	
	public String toString() {
		return record + "\t" + user + "\n";
	}
}