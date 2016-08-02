package factory;

/**
 * Created by Yao on 2015/2/25.
 */
public class PizzaTestDrive {

    public static void main(String[] args){
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore= new ChicagoPizzaStore();

        Pizza pizza= nyStore.orderPizza("cheese");
        System.out.println(pizza.toString());

        pizza= chicagoStore.orderPizza("cheese");
        System.out.println(pizza.toString());
    }
}
