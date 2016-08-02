package Iteration.use;

import Iteration.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yao on 2015/4/12.
 */
public class ListMenu implements IMenu {

    ArrayList<MenuItem> menuItems;

    public ListMenu() {
        this.menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("List_A", "A", true, 1.1));
        menuItems.add(new MenuItem("List_B", "B", true, 1.2));
        menuItems.add(new MenuItem("List_C", "C", true, 3.1));
        menuItems.add(new MenuItem("List_D", "D", true, 1.44));
        menuItems.add(new MenuItem("List_E", "E", true, 2.5));
    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }
}
