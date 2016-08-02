package yao.study.java.reflection.enums;

/**
 * Created by Administrator on 2014/11/20.
 */
public enum NewColor {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

    private String colorName;
    private int type;

    NewColor(String colorName, int type) {
        this.colorName = colorName;
        this.type = type;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static String getName(int type){
        for(NewColor nc: NewColor.values()){
            if(nc.getType()== type){
                return nc.getColorName();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "NewColor{" +
                "colorName='" + colorName + '\'' +
                ", type=" + type +
                '}';
    }
}
