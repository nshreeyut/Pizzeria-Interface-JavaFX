package com.example.project4.gui;

import com.example.project4.management.OrderManager;
import com.example.project4.pizzeria.Pizza;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller class for handling the Order Detail View in the GUI.
 * Manages the display of the current order details, including the list of pizzas,
 * subtotal, tax, and total price. Provides functionality to remove pizzas, clear the order,
 * place the order, and navigate to other views.
 * @author Shreeyut
 * @author Andy
 */
public class OrderDetailController {
    @FXML
    private ListView<String> pizzaListView;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label totalLabel;
    @FXML

    private OrderManager orderManager;
    private Main mainApp;

    /**
     * Sets the main application reference.
     * @param mainApp The main application instance.
     */
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Navigates back to the main Order View.
     */
    public void onBackToOrderButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderView();
        }
    }

    /**
     * Navigates to the Order History View.
     */
    public void onViewOrderHistoryButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderHistoryView();
        }
    }

    /**
     * Sets the OrderManager instance for managing orders.
     * @param orderManager The OrderManager instance.
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
        refreshOrderList();
    }

    /**
     * Refreshes the list of pizzas in the current order and updates the subtotal, tax, and total labels.
     */
    public void refreshOrderList() {
        pizzaListView.getItems().clear();
        for (Pizza pizza : orderManager.getCurrentOrder().getPizzas()) {
            pizzaListView.getItems().add(pizza.toString());
        }
        double subtotal = orderManager.getCurrentOrder().calculateTotal() / 1.06625;
        double tax = subtotal * 0.06625;
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", orderManager.getCurrentOrder().calculateTotal()));
    }

    /**
     * Removes the selected pizza from the current order.
     * Updates the order details view after the pizza is removed.
     */
    @FXML
    public void onRemoveSelectedPizzaButtonClicked() {
        int selectedIndex = pizzaListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            orderManager.getCurrentOrder().getPizzas().remove(selectedIndex);
            refreshOrderList();

        }
    }

    /**
     * Clears the current order and updates the order details view.
     * Resets the subtotal, tax, and total labels to $0.00.
     */
    @FXML
    public void onClearOrderButtonClicked() {
        orderManager.clearCurrentOrder();
        refreshOrderList();
        subtotalLabel.setText("$0.00");
        taxLabel.setText("$0.00");
        totalLabel.setText("$0.00");
    }

    /**
     * Places the current order, adds it to the historical orders,
     * and refreshes the order details view.
     */
    @FXML
    public void onPlaceOrderButtonClicked() {
        orderManager.placeOrder();
        refreshOrderList();
    }

}