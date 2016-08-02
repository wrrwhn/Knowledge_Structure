package generic.clean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creator: Yao
 * Date:    2016/7/23
 * For:
 * Other:
 */
public class LostInfo {

    public static void main(String[] args) {

        String str = new String();
        Fix<String> fix = new Fix<>();
        List<Frob> list = new ArrayList<>();
        Quark<Frob> quark = new Quark<>();
        Practice<String, Integer> practice = new Practice<>();

        System.out.println(Arrays.toString(str.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(fix.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(practice.getClass().getTypeParameters()));
    }
}

class Fix<String> {
}

class Frob {
}

class Quark<Q> {
}

class Practice<P, Q> {
}
