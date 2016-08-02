package Iteration.use;

import java.util.Iterator;

/**
 * Created by Yao on 2015/4/12.
 */
public class ArrayMenu implements IMenu{

    Iterator iterator;

    public ArrayMenu() {
        this.iterator = new ArrayIterator();
    }

    @Override
    public Iterator createIterator() {
        return iterator;
    }
}
