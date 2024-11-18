package com.example.project4.gui;

import com.example.project4.management.OrderManager;
import com.example.project4.management.Order;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for handling the Order History view.
 * Manages the user interface for displaying historical orders, exporting orders to a file,
 * clearing the history, and canceling selected orders.
 * @author Shreeyut
 * @author Andy
 */
public class OrderHistoryController {
    private Main mainApp;
    private OrderManager orderManager;

    @FXML
    private ListView<String> orderHistoryListView;
    @FXML
    private Label statusLabel;

    /**
     * Sets the reference to the main application.
     * @param mainApp The main application instance.
     */
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handles the export of historical orders to a text file.
     * Opens a file chooser dialog for the user to select the save location.
     * Updates the status label with the result of the export operation.
     */
    @FXML
    private void onExportButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders to File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showSaveDialog(new Stage());
        if (selectedFile != null) {
            try {
                orderManager.exportOrders(selectedFile.getAbsolutePath());
                statusLabel.setText("Orders exported successfully to " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                statusLabel.setText("Error exporting orders: " + e.getMessage());
            }
        } else {
            statusLabel.setText("Export canceled.");
        }
    }

    /**
     * Clears the list of historical orders and updates the ListView.
     * Updates the status label based on whether the history was cleared successfully.
     */
    public void clearHistoryButton() {
        if (orderManager != null && !orderManager.getHistoricalOrders().isEmpty()) {
            orderManager.getHistoricalOrders().clear();
            loadOrderHistory(); // Refresh the ListView
            statusLabel.setText("Historical orders cleared.");
        } else {
            statusLabel.setText("No historical orders to clear.");
        }
    }

    /**
     * Sets the OrderManager instance for managing orders.
     * Initializes the historical order data and loads it into the ListView.
     * @param orderManager The OrderManager instance.
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
        loadOrderHistory(); // Load the historical orders into the ListView
    }

    /**
     * Loads the list of historical orders into the ListView.
     * Updates the status label based on whether the orders were successfully loaded.
     */
    private void loadOrderHistory() {
        orderHistoryListView.getItems().clear();
        if (orderManager != null) {
            for (Order order : orderManager.getHistoricalOrders()) {
                orderHistoryListView.getItems().add(order.toString());
            }
            statusLabel.setText("Historical orders loaded.");
        } else {
            statusLabel.setText("OrderManager not available.");
        }
    }

    /**
     * Navigates back to the Order View.
     * This method is triggered when the user clicks the "Back to Order" button.
     */
    public void onBackToOrderButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderView();
        }
    }

    /**
     * Loads the selected historical order into the current order and navigates to the Order Details view.
     * Updates the status label to indicate the selected order was loaded.
     */
    public void onOrderList() {
        int selectedIndex = orderHistoryListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && orderManager != null) {
            Order selectedOrder = orderManager.getHistoricalOrders().get(selectedIndex);
            orderManager.setCurrentOrder(selectedOrder); // Replace current order with selected historical order
            mainApp.showOrderDetailView(); // Navigate to order details view
            statusLabel.setText("Order loaded into details.");
        }
    }

    /**
     * Cancels the selected historical order.
     * Removes the order from the historical orders list and updates the ListView.
     * Updates the status label based on whether the cancellation was successful.
     */

    public void cancelOrderButtonClicked() {
        int selectedIndex = orderHistoryListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && orderManager != null) {
            Order selectedOrder = orderManager.getHistoricalOrders().get(selectedIndex);
            boolean isCanceled = orderManager.cancelOrder(selectedOrder);
            if (isCanceled) {
                loadOrderHistory();
                statusLabel.setText("Order canceled successfully.");
            } else {
                statusLabel.setText("Failed to cancel the order.");
            }
        } else {
            statusLabel.setText("No order selected for cancellation.");
        }
    }
}
