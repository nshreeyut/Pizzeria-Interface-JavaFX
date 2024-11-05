package com.example.project4.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class OrderHistoryController {
    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void onBackToOrderButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderView();
        }
    }
}