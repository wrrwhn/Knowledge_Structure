package forkJoin.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yao on 2015/11/25.
 */
public class ProductFactory {

    public static List<Product> Generator(int size){
        List<Product> productList= new ArrayList<Product>();
        for (int i = 0; i < size; i++) {
            productList.add(new Product("Product_"+ i, 10));
        }

        return productList;
    }
}
