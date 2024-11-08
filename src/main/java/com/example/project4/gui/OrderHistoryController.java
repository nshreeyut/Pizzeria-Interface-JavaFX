package com.example.project4.gui;

import com.example.project4.management.OrderManager;
import com.example.project4.management.Order;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

public class OrderHistoryController {
    private Main mainApp;
    private OrderManager orderManager; // Reference to OrderManager

    @FXML
    private ListView<String> orderHistoryListView; // ListView to display historical orders
    @FXML
    private Label statusLabel;

    // Method to set the main application reference
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    // Method to set the OrderManager instance
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
        loadOrderHistory(); // Load the historical orders into the ListView
    }

    // Method to load historical orders into the ListView
    private void loadOrderHistory() {
        orderHistoryListView.getItems().clear();

        if (orderManager != null) {
            for (Order order : orderManager.getHistoricalOrders()) {
                orderHistoryListView.getItems().add(order.toString()); // Add each order's string representation
            }
            statusLabel.setText("Historical orders loaded.");
        } else {
            statusLabel.setText("OrderManager not available.");
        }
    }

    // Method to go back to the main order view
    public void onBackToOrderButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderView();
        }
    }
}
