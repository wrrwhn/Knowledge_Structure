package Iteration.combination;

/**
 * Created by Yao on 2015/4/16.
 */
public class Waitress {

    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenus(){
        allMenus.print();
    }
}
