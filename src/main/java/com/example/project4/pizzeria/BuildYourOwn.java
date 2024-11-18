package com.example.project4.pizzeria;
import java.util.List;

/**
 * Represents a customizable pizza where the user can build their own pizza
 * by choosing the desired toppings. This class extends the abstract {@link Pizza} class.
 * The price of the pizza is based on the selected size and the number of toppings.
 * @author Shreeyut
 * @author Andy
 */
public class BuildYourOwn extends Pizza {

    private static final double TOPPING_COST = 1.69;


    /**
     * Constructor for BuildYourOwn pizza.
     * Initializes a pizza with no predefined toppings.
     * The toppings can be added dynamically using the {@link #setToppings(List)} method.
     */
    public BuildYourOwn() {

    }

    /**
     * Calculates the price of the Build Your Own pizza based on its size and the number of toppings.
     * The base price varies by size, and each topping incurs an additional cost.
     * @return The total price of the pizza as a {@code double} value.
     */
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

    /**
     * Returns the cost of a single topping for the Build Your Own pizza.
     * @return The cost of one topping as a {@code double} value.
     */
    public static double getToppingCost() {
        return TOPPING_COST;
    }

    /**
     * Returns a string representation of the Build Your Own pizza,
     * including its size, crust type, and list of selected toppings.
     * @return A formatted string describing the Build Your Own pizza.
     */
    @Override
    public String toString() {
        return "Build Your Own Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}
