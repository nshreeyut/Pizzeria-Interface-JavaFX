package com.example.project4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("RU Pizzeria Management System");
        showOrderView();
        primaryStage.show();
    }

    public void showOrderView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/project4/order-view.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            OrderController controller = loader.getController();
            controller.setMain(this);  // Pass main app to controller if needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOrderDetailView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/project4/orderdetail-view.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            OrderDetailController controller = loader.getController();
            controller.setMain(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOrderHistoryView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/project4/orderhistory-view.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            OrderHistoryController controller = loader.getController();
            controller.setMain(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}