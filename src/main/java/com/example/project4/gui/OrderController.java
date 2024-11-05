package com.example.project4.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderController {
    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void onNextButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderDetailView();
        }
    }
}