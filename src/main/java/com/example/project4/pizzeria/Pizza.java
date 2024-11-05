package com.example.project4.pizzeria;
import com.example.project4.management.List;

public abstract class Pizza {
    private List<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();
}

