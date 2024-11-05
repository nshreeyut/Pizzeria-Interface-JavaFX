package com.example.project4.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class OrderManager {
    private List<Order> currentOrders;  // List of current orders
    private List<Order> historicalOrders; // List of historical orders

    /**
     * Constructor for OrderManager.
     * Initializes empty lists for current and historical orders.
     */
    public OrderManager() {
        this.currentOrders = new List<>();
        this.historicalOrders = new List<>();
    }

    /**
     * Places a new order by adding it to the current orders list.
     * @param order The new order to be added.
     */
    public void placeOrder(Order order) {
        currentOrders.add(order);
    }

    /**
     * Browses through current orders.
     * @return An iterable list of current orders.
     */
    public Iterable<Order> browseOrders() {
        return currentOrders;
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
                iterator.remove();  // Remove from current orders
                historicalOrders.add(order);  // Add to historical orders
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
}
