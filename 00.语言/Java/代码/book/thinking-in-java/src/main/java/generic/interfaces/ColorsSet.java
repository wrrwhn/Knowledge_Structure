package generic.interfaces;

import java.util.EnumSet;
import java.util.Set;

/**
 * Creator: Yao
 * Date:    2016/7/20
 * For:     新的东东哟！
 * Other:
 */
public class ColorsSet {

    public static void main(String[] args) {

        Set<Colors> set1 = EnumSet.range(Colors.ZINC, Colors.BRILLIANT_RED);
        Set<Colors> set2 = EnumSet.range(Colors.ZINC, Colors.ULTRAMARINE);

        System.out.println(set1);
        System.out.println(set2);
    }
}
