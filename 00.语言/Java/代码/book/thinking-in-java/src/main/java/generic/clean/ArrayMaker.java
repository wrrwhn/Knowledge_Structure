package generic.clean;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class ArrayMaker<T> {

    private Class<T> clazz;

    public ArrayMaker(Class<T> clazz) {
        this.clazz = clazz;
    }

    T[] create(int size) {
        return (T[]) Array.newInstance(clazz, size);
    }


    public static void main(String[] args) {
        ArrayMaker<String> arrayMaker = new ArrayMaker<>(String.class);
        String[] strArray = arrayMaker.create(10);
        System.out.println(Arrays.toString(strArray));
    }
}
