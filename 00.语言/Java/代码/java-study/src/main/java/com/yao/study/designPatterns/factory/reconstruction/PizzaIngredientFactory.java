package com.yao.study.designPatterns.factory.reconstruction;

import com.yao.study.designPatterns.factory.reconstruction.material.*;

/**
 * Created by Yao on 2015/3/10.
 */
public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();
}
