package com.example.project4.management;

import com.example.project4.pizzeria.Pizza;

public class Order {
    private static int nextOrderNumber = 0;  // Static field to generate unique order numbers
    private int number;                      // Unique order number for each order
    private List<Pizza> pizzas;              // Custom List of pizzas in the order

    /**
     * Constructs an empty Order with a unique order number and an empty list of pizzas.
     */
    public Order() {
        this.number = nextOrderNumber++;
        this.pizzas = new List<>();
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
    public boolean removePizza(Pizza pizza) {
        if (pizzas.contains(pizza)) {
            pizzas.remove(pizza);
            return true;
        }
        return false;
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
        double tax = subtotal * 0.06625; // New Jersey tax rate
        return subtotal + tax;
    }

    /**
     * Returns the order number.
     * @return the order number.
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
        sb.append("Total: $").append(String.format("%.2f", calculateTotal()));
        return sb.toString();
    }
}

