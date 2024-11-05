package com.example.project4.pizzeria;

import com.example.project4.management.List;

public abstract class Pizza {
    private List<Topping> toppings; // List of toppings on the pizza
    private Crust crust;  // Crust type for the pizza
    private Size size;    // Size of the pizza (Small, Medium, Large)

    // Constructor
    public Pizza() {
        this.toppings = new List<>(); // Initialize toppings list
    }

    // Abstract method to calculate the price of the pizza
    public abstract double price();

    // Setter for crust
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    // Getter for crust
    public Crust getCrust() {
        return crust;
    }

    // Setter for size
    public void setSize(Size size) {
        this.size = size;
    }

    // Getter for size
    public Size getSize() {
        return size;
    }

    // Method to add a topping (used in BuildYourOwn pizza)
    public void addTopping(Topping topping) {
        if (toppings.size() < 7) {  // Max 7 toppings for BuildYourOwn
            toppings.add(topping);
        } else {
            System.out.println("Maximum number of toppings reached.");
        }
    }

    // Method to remove a topping
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    // Getter for toppings list
    public List<Topping> getToppings() {
        return toppings;
    }

    // Method to clear all toppings (useful for resetting a pizza)
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
