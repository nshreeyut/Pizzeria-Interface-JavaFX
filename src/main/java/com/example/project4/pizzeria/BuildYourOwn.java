package com.example.project4.pizzeria;
import java.util.List;

public class BuildYourOwn extends Pizza {

    private static final double TOPPING_COST = 1.69;

    public BuildYourOwn() {
        // Starts with no toppings; toppings can be added dynamically
    }

    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL:
                basePrice = 8.99;
                break;
            case MEDIUM:
                basePrice = 10.99;
                break;
            case LARGE:
                basePrice = 12.99;
                break;
            default:
                basePrice = 0;
        }
        double toppingCost = getToppings().size() * TOPPING_COST;
        return basePrice + toppingCost;
    }

    /**
     * Sets the list of toppings for the pizza.
     * This method is only used for Build Your Own pizzas.
     * @param toppings The list of toppings to set for this pizza.
     */
    public void setToppings(List<Topping> toppings) {
        getToppings().clear(); // Clear any existing toppings
        getToppings().addAll(toppings); // Add new toppings
    }

    public static double getToppingCost() {
        return TOPPING_COST;
    }

    @Override
    public String toString() {
        return "Build Your Own Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}
