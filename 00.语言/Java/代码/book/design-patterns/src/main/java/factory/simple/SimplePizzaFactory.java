package factory.simple;

/**
 * Created by Yao on 2015/2/13.
 */
public class SimplePizzaFactory {

    public Pizza craetePizza(String type){
        Pizza pizza= null;

            switch (type){
                case "cheese":
                    pizza= new CheesePizza();
                    break;
                default:
                    pizza= new ClamPizza();
            }
        return pizza;
    }
}
