package factory.reconstruction;

/**
 * Created by Yao on 2015/3/11.
 */
public class NYPizzaStore extends PizzaStore
{
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza= null;
        PizzaIngredientFactory ingredientFactory= new NYPizzaIngredientFactory();

        switch (type){
            case "cheese":
                pizza= new CheesePizza(ingredientFactory);
                pizza.setName("New York Style Cheese Pizza");
                break;
            default:
                pizza= new ClamPizza(ingredientFactory);
                pizza.setName("New York Style Clams Pizza");
                break;
        }

        return pizza;
    }
}
