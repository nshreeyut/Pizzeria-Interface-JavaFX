package com.example.project4.management;

import com.example.project4.pizzeria.Pizza;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderNumber = 0;  // Static field to generate unique order numbers
    private int number;                      // Unique order number for each order
    private List<Pizza> pizzas;              // List of pizzas in the order

    /**
     * Constructs an empty Order with a unique order number and an empty list of pizzas.
     */
    public Order() {
        this.number = nextOrderNumber++;
        this.pizzas = new ArrayList<>(); // Initialize with ArrayList
    }

    /**
     * Adds a pizza to the order.
     * @param pizza The pizza to add.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a specific pizza from the order.
     * @param pizza The pizza to remove.
     * @return true if the pizza was successfully removed, false otherwise.
     */
    // it just check if the pizza is removed or not
    public boolean removePizza(Pizza pizza) {
        return pizzas.remove(pizza);
    }

    /**
     * Clears all pizzas from the order.
     */
    public void clearOrder() {
        pizzas.clear();
    }

    /**
     * Calculates the total price of the order including tax.
     * @return The total price including tax.
     */
    public double calculateTotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        double tax = subtotal * 0.06625; // NJ sales tax rate
        return subtotal + tax;
    }

    /**
     * Returns the list of pizzas in the order.
     * @return A list of pizzas in the order.
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Returns the order number.
     * @return The order number.
     */
    public int getOrderNumber() {
        return number;
    }

    /**
     * Gets a string representation of the order details.
     * @return A string with the order details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(number).append("\n");
        for (Pizza pizza : pizzas) {
            sb.append(pizza.toString()).append("\n");
        }
        sb.append("Order Total: $").append(String.format("%.2f", calculateTotal()));
        return sb.toString();
    }
}
