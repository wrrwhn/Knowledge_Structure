package generic.compensation.template;

/**
 * Creator: Yao
 * Date:    2016/7/24
 * For:
 * Other:
 */
public class CreatorGeneric{

    public static void main(String[] args) {
        Creator creator= new Creator();
        creator.f();
    }
}

abstract class GenericWithCreate<T> {

    final T element;

    public GenericWithCreate() {
        element = create();
    }

    abstract T create();
}

class X {}

class Creator extends GenericWithCreate<X>{

    @Override
    X create() {
        return new X();
    }

    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}