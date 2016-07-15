package generic.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creator: Yao
 * Date:    2016/7/15
 * For:
 * Other:
 */
public class New {

    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    public static void test(Map<String, List<? extends String>> mapList) {
    }

    public static void main(String[] args) {

        GenericMehods genericMehods = new GenericMehods();

        Map<String, String> map = New.map();
        Map<String, List<? extends String>> mapLst = New.map();
        genericMehods.printClass(map);
        genericMehods.printClass(mapLst);
        genericMehods.printClass(New.map());

        test(New.map());
    }
}
