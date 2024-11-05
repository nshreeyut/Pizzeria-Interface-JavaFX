package com.example.project4.pizzeria;

public class BuildYourOwn extends Pizza {

    private static final double TOPPING_COST = 1.69;

    public BuildYourOwn() {
        // Starts with no toppings; toppings can be added dynamically
    }

    @Override
    public double price() {
        // Define base pricing based on size
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

    @Override
    public String toString() {
        return "Build Your Own Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}
