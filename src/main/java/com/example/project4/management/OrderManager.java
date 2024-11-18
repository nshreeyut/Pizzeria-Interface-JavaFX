package com.example.project4.management;

import com.example.project4.pizzeria.Pizza;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> historicalOrders;    // List of past (historical) orders
    private Order currentOrder;              // The active order being created

    /**
     * Constructor for OrderManager.
     * Initializes empty lists for current and historical orders.
     * @author Shreeyut
     * @author Andy
     */
    public OrderManager() {
        this.historicalOrders = new ArrayList<>();
        this.currentOrder = new Order();            // Start with a new active order
    }

    /**
     * Returns the active order being built.
     * @return The current order.
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Returns the list of historical orders.
     * @return A list of historical (past) orders.
     */
    public List<Order> getHistoricalOrders() {
        return historicalOrders;
    }

    /**
     * Adds a pizza to the current order.
     * @param pizza The pizza to add.
     */
    public void addPizzaToCurrentOrder(Pizza pizza) {
        if (pizza != null) {
            currentOrder.addPizza(pizza);
        }
    }

    /**
     * Places the current order, adds it to the current orders list, and resets the active order.
     */
    public boolean placeOrder() {
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            if (!historicalOrders.contains(currentOrder)) { // Ensure no duplicates
                historicalOrders.add(currentOrder);        // Add to historical orders
            }
            this.currentOrder = new Order();               // Reset currentOrder for the next transaction
            return true; // Order successfully placed
        }
        return false; // Order was not placed
    }

    /**
     * Cancels the specified order by removing it from the historical orders list.
     * @param order The order to be canceled.
     * @return true if the order was found and canceled, false otherwise.
     */
    public boolean cancelOrder(Order order) {
        if (order == null) {
            return false;
        }
        if (historicalOrders.contains(order)) {
            historicalOrders.remove(order);
            return true;
        }
        return false;
    }

    /**
     * Exports all historical orders to a text file.
     * @param filename The name of the file to export to.
     * @throws IOException if there is an error writing to the file.
     */
    public void exportOrders(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : historicalOrders) {
                writer.write(order.toString());
                writer.newLine();
                writer.newLine();
            }
        }
    }

    /**
     * Clears the current order, resetting to a new empty order.
     */
    public void clearCurrentOrder() {
        if (currentOrder != null) {
            currentOrder.clearOrder();  // Use clearOrder to empty the list of pizzas
        }
    }

    /**
     * Sets the current order to the order passed in.
     * @param order Order passed in
     */
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

}
