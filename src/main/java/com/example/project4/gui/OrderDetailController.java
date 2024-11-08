package com.example.project4.gui;

import com.example.project4.management.OrderManager;
import com.example.project4.pizzeria.Pizza;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class OrderDetailController {
    @FXML
    private ListView<String> pizzaListView;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label totalLabel;

    private OrderManager orderManager;
    private Main mainApp;

    // Method to set Main reference
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    // Example method to navigate back to Order View
    public void onBackToOrderButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderView();
        }
    }

    // Example method to navigate to Order History View
    public void onViewOrderHistoryButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderHistoryView();
        }
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
        refreshOrderList();
    }

    public void refreshOrderList() {
        pizzaListView.getItems().clear();

        // Get pizzas from the current order
        for (Pizza pizza : orderManager.getCurrentOrder().getPizzas()) {
            pizzaListView.getItems().add(pizza.toString());
        }

        // Update subtotal, tax, and total labels
        double subtotal = orderManager.getCurrentOrder().calculateTotal() / 1.06625;
        double tax = subtotal * 0.06625;
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", orderManager.getCurrentOrder().calculateTotal()));
    }
}