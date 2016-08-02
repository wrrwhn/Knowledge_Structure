package Iteration.combination;

/**
 * Created by Yao on 2015/4/15.
 */
public class MenuItem extends MenuComponent{
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.println("MenuItem{" +
                "price=" + price +
                ", vegetarian=" + vegetarian +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}');
    }
}
