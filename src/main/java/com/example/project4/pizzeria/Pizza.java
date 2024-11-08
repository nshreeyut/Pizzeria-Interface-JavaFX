package com.example.project4.pizzeria;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    private List<Topping> toppings; // List of toppings on the pizza
    private Crust crust;  // Crust type for the pizza
    private Size size;    // Size of the pizza (Small, Medium, Large)

    public Pizza() {
        this.toppings = new ArrayList<>(); // Initialize with ArrayList
    }

    public abstract double price();

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void addTopping(Topping topping) {
        if (toppings.size() < 7) {  // Max 7 toppings for BuildYourOwn
            toppings.add(topping);
        } else {
            System.out.println("Maximum number of toppings reached.");
        }
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void clearToppings() {
        toppings.clear();
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "toppings=" + toppings +
                ", crust=" + crust +
                ", size=" + size +
                '}';
    }
}
