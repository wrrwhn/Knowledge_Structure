package factory.reconstruction;

/**
 * Created by Yao on 2015/3/10.
 */
public class CheesePizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println(String.format("Preparing %s", name));
        dough= ingredientFactory.createDough();
        sauce= ingredientFactory.createSauce();
        cheese= ingredientFactory.createCheese();
    }
}
