package com.yao.study.designPatterns.Iteration.combination;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yao on 2015/4/15.
 */
public class Menu extends MenuComponent {
    ArrayList menus = new ArrayList();
    String name;
    String description;

    public Menu(String description, String name) {
        this.description = description;
        this.name = name;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menus.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menus.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return (MenuComponent) menus.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.println("Menu{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'');
        Iterator ite= menus.iterator();
        while (ite.hasNext()){
            MenuComponent component= (MenuComponent) ite.next();
            component.print();
        }
        System.out.println('}');

    }
}
