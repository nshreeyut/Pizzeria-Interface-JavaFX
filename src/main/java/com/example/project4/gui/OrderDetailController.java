package com.example.project4.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderDetailController {
    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void onBackToOrderButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderView();
        }
    }

    public void onViewOrderHistoryButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderHistoryView();
        }
    }
}