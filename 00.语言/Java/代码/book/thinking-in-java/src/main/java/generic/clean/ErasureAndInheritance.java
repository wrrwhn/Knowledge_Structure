package generic.clean;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class ErasureAndInheritance {

    public static void main(String[] args) {
        Derived2 derived = new Derived2();
        Object obj = derived.getElement();
        derived.setElement(obj);
    }
}

class GenericBasic<T> {

    private T element;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}

class Derived1<T> extends GenericBasic<T> {
}

class Derived2 extends GenericBasic {
}