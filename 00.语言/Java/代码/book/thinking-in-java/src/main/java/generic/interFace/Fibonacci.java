package generic.interFace;


/**
 * Creator: Yao
 * Date:    2016/7/14
 * For:
 * Other:
 */
public class Fibonacci implements Generator<Integer> {

    private int count = 0;

    @Override
    public Integer next() {
        return fibonacci(count++);
    }

    private int fibonacci(int n) {
        if (n < 2) return 1;
        else return fibonacci(n - 2) + fibonacci(n - 1);
    }


    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 18; i++) {
            System.out.println(fibonacci.next());
        }
    }
}
