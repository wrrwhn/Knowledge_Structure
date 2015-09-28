package com.yao.study.designPatterns.Iteration;

import java.util.ArrayList;

/**
 * Created by Yao on 2015/4/8.
 */
public class PancakeHousemenu {

    ArrayList menuItems;

    public PancakeHousemenu() {
        this.menuItems = menuItems;

        addItem("AAAAA", "AAAAAAAAAAAA", true, 2.99);
        addItem("BBB", "BBBBBBBBBB", false, 2.99);
        addItem("CCC", "CCCCCCCC", true, 3.49);
        addItem("DDD", "DDDDDDDDD", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        menuItems.add(new MenuItem(name, description, vegetarian, price));
    }

    public ArrayList getMenuItems(){
        return menuItems;
    }
}
