package com.example.project4.gui;

import com.example.project4.management.OrderManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * The Main class serves as the entry point for the RU Pizzeria Management System application.
 * It initializes the primary stage, manages scene transitions, and shares a single instance
 * of {@link OrderManager} across all controllers to handle order-related operations.
 * @author Shreeyut
 * @author Andy
 */
public class Main extends Application {
    private Stage primaryStage;
    private OrderManager orderManager; // Shared OrderManager for all controllers

    /**
     * Starts the application by initializing the primary stage, creating the shared
     * {@link OrderManager}, and displaying the initial Order View.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.orderManager = new OrderManager(); // Initialize OrderManager here
        primaryStage.setTitle("RU Pizzeria Management System");
        showOrderView();
        primaryStage.show();
    }

    /**
     * Displays the Order View by loading its FXML file and setting it on the primary stage.
     * Passes the {@link Main} instance and the shared {@link OrderManager} to the {@link OrderController}.
     */
    public void showOrderView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/order-view.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));

            // Set up OrderController with references to Main and OrderManager
            OrderController controller = loader.getController();
            controller.setMain(this);  // Pass main app to controller if needed
            controller.setOrderManager(orderManager); // Share the OrderManager with OrderController
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Order Detail View by loading its FXML file and setting it on the primary stage.
     * Passes the {@link Main} instance and the shared {@link OrderManager} to the {@link OrderDetailController}.
     */
    public void showOrderDetailView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/orderdetail-view.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));

            // Set up OrderDetailController with references to Main and OrderManager
            OrderDetailController controller = loader.getController();
            controller.setMain(this);
            controller.setOrderManager(orderManager); // Share the OrderManager with OrderDetailController
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Order History View by loading its FXML file and setting it on the primary stage.
     * Passes the {@link Main} instance and the shared {@link OrderManager} to the {@link OrderHistoryController}.
     */
    public void showOrderHistoryView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/orderhistory-view.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));

            // Set up OrderHistoryController with references to Main and OrderManager
            OrderHistoryController controller = loader.getController();
            controller.setMain(this);
            controller.setOrderManager(orderManager); // Share the OrderManager with OrderHistoryController
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main entry point of the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
