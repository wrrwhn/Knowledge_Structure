package generic.compensation.cast;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class GenericArray<T> {

    private Object[] array;

    public GenericArray(int size) {
        this.array = new Object[size];
    }

    public T get(int idx) {
        return (T) array[idx];
    }

    public void put(int idx, T obj) {
        this.array[idx] = obj;
    }

    public T[] rep() {
        return (T[]) array;
    }


    public static void main(String[] args) {
        int size = 10;
        GenericArray<Integer> gai = new GenericArray<>(size);
        for (int i = 0; i < size; i++) {
            gai.put(i, i);
        }
        for (int i = 0; i < size; i++) {
            System.out.println(gai.get(i));
        }
        try {
            Integer[] ia = gai.rep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
