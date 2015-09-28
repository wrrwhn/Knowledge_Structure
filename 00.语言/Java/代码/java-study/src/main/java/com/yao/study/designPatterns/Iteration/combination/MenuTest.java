package com.yao.study.designPatterns.Iteration.combination;

/**
 * Created by Yao on 2015/4/16.
 */
public class MenuTest {

    public static void main(String args[]){
        MenuComponent menuA= new Menu("A", "AA");
        MenuComponent menuB= new Menu("B", "BB");
        MenuComponent menuC= new Menu("C", "CC");
        MenuComponent menuD= new Menu("D", "DD");

        MenuComponent menus= new Menu("All", "all menus");
        menus.add(menuA);
        menus.add(menuB);
        menus.add(menuC);

        menuC.add(new MenuItem("C1", "MenuC item 1", true, 1.59));
        menuC.add(menuD);
        menuC.add(new MenuItem("D1", "MenuD item 1", false, 2.01));

        Waitress waitress= new Waitress(menus);
        waitress.printMenus();
    }
}
