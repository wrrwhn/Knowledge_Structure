package com.yao.study.designPatterns.Iteration;

/**
 * Created by Yao on 2015/4/11.
 */
public class DinerMenuIterator implements Iterator {

    MenuItem[] items;
    int pos= 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if(pos>= items.length|| items[pos]== null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem item= items[pos];
        pos++;
        return item;
    }
}
