package generic.interFace;

import java.util.Iterator;

/**
 * Creator: Yao
 * Date:    2016/7/14
 * For:
 * Other:
 */
public class IteratorFabonacci extends Fibonacci implements Iterator<Integer> {

    @Override
    public boolean hasNext() {
        return this.count > 0;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer next() {
        this.count--;
        return super.next();
    }

    private int count;

    public IteratorFabonacci(int count) {
        this.count = count;
    }

    public static void main(String[] args) {

        IteratorFabonacci fabonacci = new IteratorFabonacci(18);
        while (fabonacci.hasNext()) {
            System.out.println(fabonacci.next());
        }
    }
}
