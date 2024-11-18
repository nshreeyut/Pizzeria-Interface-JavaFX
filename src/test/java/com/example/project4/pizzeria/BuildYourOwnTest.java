package com.example.project4.pizzeria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class BuildYourOwnTest {

    @Test
    void testPriceNoToppingsSmall() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);
        pizza.setToppings(Arrays.asList());
        double expectedPrice = 8.99;
        assertEquals(expectedPrice, pizza.price(), 0.001);
    }

    @Test
    void testPriceNoToppingsMedium() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        pizza.setToppings(Arrays.asList());
        double expectedPrice = 10.99;
        assertEquals(expectedPrice, pizza.price(), 0.001);
    }

    @Test
    void testPriceMaxToppingsLarge() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.LARGE);
        pizza.setToppings(Arrays.asList(
                Topping.PEPPERONI, Topping.SAUSAGE, Topping.ONION,
                Topping.BEEF, Topping.BACON, Topping.BLACK_OLIVES, Topping.GREEN_PEPPER
        ));
        double expectedPrice = 12.99 + (7 * 1.69);
        assertEquals(expectedPrice, pizza.price(), 0.001);
    }

    @Test
    void testPriceOneToppingSmall() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);
        pizza.setToppings(Arrays.asList(Topping.PEPPERONI));
        double expectedPrice = 8.99 + 1.69;
        assertEquals(expectedPrice, pizza.price(), 0.001);
    }

    @Test
    void testPriceMultipleToppingsMedium() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        pizza.setToppings(Arrays.asList(Topping.BACON, Topping.BLACK_OLIVES, Topping.PINEAPPLE));
        double expectedPrice = 10.99 + (3 * 1.69);
        assertEquals(expectedPrice, pizza.price(), 0.001);
    }
}
