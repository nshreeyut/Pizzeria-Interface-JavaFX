package com.example.project4.management;

import com.example.project4.pizzeria.Pizza;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderManager {
    private List<Order> currentOrders;       // List of current active orders
    private List<Order> historicalOrders;    // List of past (historical) orders
    private Order currentOrder;              // The active order being created

    /**
     * Constructor for OrderManager.
     * Initializes empty lists for current and historical orders.
     */
    public OrderManager() {
        this.currentOrders = new ArrayList<>();     // Initialize with ArrayList
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
    public void placeOrder() {
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            currentOrders.add(currentOrder);
            this.currentOrder = new Order(); // Start a new order after placing the current one
        } else {
            System.out.println("Order is empty. Cannot place an empty order.");
        }
    }

    /**
     * Cancels an order by removing it from the current orders and adding it to the historical orders.
     * @param orderNumber The order number to be canceled.
     * @return true if the order was found and canceled, false otherwise.
     */
    public boolean cancelOrder(int orderNumber) {
        Iterator<Order> iterator = currentOrders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderNumber() == orderNumber) {
                iterator.remove();                  // Remove from current orders
                historicalOrders.add(order);        // Move to historical orders
                return true;
            }
        }
        return false; // Order not found
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
            }
        }
    }

    /**
     * Clears the current order, resetting to a new empty order.
     */
    public void clearCurrentOrder() {
        this.currentOrder = new Order(); // Reinitialize to start fresh
    }
}
