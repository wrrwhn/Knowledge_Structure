package Iteration;

/**
 * Created by Yao on 2015/4/11.
 */
public class DinerMenu {

    static final int MAX_ITEMS= 6;
    int numberOfItems= 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems= new MenuItem[MAX_ITEMS];

        addItem("A", "AA", true, 1);
        addItem("B", "AB", true, 2);
        addItem("C", "CCC", true, 3);
        addItem("D", "DDD", true, 4);

    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem item= new MenuItem(name, description, vegetarian, price);
        if(numberOfItems>= MAX_ITEMS){
            System.out.println("Out of menu");
        }else{
            menuItems[numberOfItems]= item;
            numberOfItems++;
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }
}
