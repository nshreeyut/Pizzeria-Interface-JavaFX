package com.example.project4.pizzeria;

public class BBQChicken extends Pizza {

    public BBQChicken() {
        getToppings().add(Topping.BBQ_CHICKEN);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.PROVOLONE);
        getToppings().add(Topping.CHEDDAR);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return 14.99;
            case MEDIUM:
                return 16.99;
            case LARGE:
                return 19.99;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "BBQ Chicken Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}
