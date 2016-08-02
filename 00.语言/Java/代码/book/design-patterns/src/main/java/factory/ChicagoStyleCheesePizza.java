package factory;

/**
 * Created by Yao on 2015/2/25.
 */
public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name= "Chicago Style Sauce and Cheese Pizza";
        dough = "Extra thick Crust Dough";
        sauce= "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella  Cheese");
    }

    @Override
    void cut() {
        System.out.println("cutting the pizza into square slices");
    }
}
