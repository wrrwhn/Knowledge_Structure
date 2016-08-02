package generic.structure;

import generic.interfaces.Generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creator: Yao
 * Date:    2016/7/22
 * For:
 * Other:
 */
public class Store extends ArrayList<Aisle> {

    public static void main(String[] args) {
        System.out.println(new Store(1, 2, 3));
    }

    public Store(int initialCapacity, int nAsile, int nShelf) {
        for (int i = 0; i < initialCapacity; i++) {
            add(new Aisle(nAsile, nShelf));
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        for (Aisle aisle : this) {
            for (Shelf shelf : aisle)
                for (Product product : shelf)
                    buffer.append(product).append("\n");
        }

        return buffer.toString();
    }
}

class Product {

    private int id;
    private String desc;
    private double price;

    public Product(int id, String desc, double price) {
        this.id = id;
        this.desc = desc;
        this.price = price;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random random = new Random(47);

        @Override
        public Product next() {
            return new Product(random.nextInt(1000), "generator-new-one", Math.round(random.nextDouble() * 1000) + 0.99);
        }
    };

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}

class Shelf extends ArrayList<Product> {

    public Shelf(int initialCapacity) {
        for (int i = 0; i < initialCapacity; i++) {
            add(Product.generator.next());
        }
    }
}

class Aisle extends ArrayList<Shelf> {

    public Aisle(int initialCapacity, int initShelfCapacity) {
        for (int i = 0; i < initialCapacity; i++) {
            add(new Shelf(initShelfCapacity));
        }
    }
}
