package com.yao.study.designPatterns.Iteration.use;

import com.yao.study.designPatterns.Iteration.MenuItem;

import java.util.Iterator;

/**
 * Created by Yao on 2015/4/12.
 */
public class ArrayIterator implements Iterator {

    int MAX_SIZE= 6;
    int pos= 0;
    MenuItem[] items;

    public ArrayIterator() {
        items= new MenuItem[MAX_SIZE];
        for(int i=0; i< MAX_SIZE; i++){
            items[i]= new MenuItem("Array_"+ i, i+ "", i%2==0, i);
        }
    }

    @Override
    public boolean hasNext() {
        return pos< MAX_SIZE && items[pos]!= null;
    }

    @Override
    public Object next() {
        MenuItem item= items[pos];
        pos++;
        return item;
    }

    @Override
    public void remove() {
        items[pos]= null;
        if(pos!= 0) {
            pos--;
        }
    }
}
