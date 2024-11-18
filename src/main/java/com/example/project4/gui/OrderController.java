package com.example.project4.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.example.project4.pizzeria.*;
import com.example.project4.management.*;
import java.util.ArrayList;

/**
 * Controller class for handling pizza ordering logic in the GUI.
 * Manages the user interface for selecting pizza type, style, size, and toppings,
 * and handles adding pizzas to the current order.
 * @author Shreeyut
 * @author Andy
 */
public class OrderController {
    private Main mainApp;
    private Pizza currentPizza;
    private PizzaFactory currentFactory;
    private String selectedStyle;
    private String selectedType;
    private OrderManager orderManager; // OrderManager instance to manage orders


    @FXML
    private ComboBox<String> pizzaTypeComboBox;
    @FXML
    private ListView<Topping> availableToppingsListView;
    @FXML
    private ListView<Topping> selectedToppingsListView;
    @FXML
    private Button addToppingButton;
    @FXML
    private Button removeToppingButton;
    @FXML
    private ComboBox<String> pizzaStyleComboBox;
    @FXML
    private ComboBox<Size> pizzaSizeComboBox;
    @FXML
    private Label PizzaTotalLabel, statusLabel;
    @FXML
    private ImageView pizzaImageView;

    /**
     * Initializes the GUI components and sets up the listeners for ComboBoxes and ListViews.
     * Called automatically after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        PizzaTotalLabel.setText("Pizza Total: $0.00");
        availableToppingsListView.getItems().addAll(Topping.values());
        pizzaTypeComboBox.getItems().addAll("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own");
        pizzaTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedType = newValue;
            onPizzaTypeSelected(newValue);
            updatePizzaImage();
        });
        pizzaStyleComboBox.getItems().addAll("New York Style", "Chicago Style");
        pizzaStyleComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedStyle = newValue;
            onPizzaStyleSelected(newValue);
            updatePizzaImage();
        });
        pizzaSizeComboBox.getItems().addAll(Size.values());
        pizzaSizeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (currentPizza != null && newValue != null) {
                currentPizza.setSize(newValue);
                updatePizzaPrice();
            }
        });
    }

    /**
     * Sets the OrderManager instance for managing orders.
     * @param orderManager The OrderManager instance.
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Adds the currently selected pizza to the order.
     * If the pizza is a Build Your Own, it sets the selected toppings.
     * Updates the status label and clears the selection.
     */
    @FXML
    public void onAddToOrderClick() {
        if (currentPizza == null || pizzaSizeComboBox.getValue() == null) {
            statusLabel.setText("Please select a pizza type, style, and size.");
            return;
        }
        if (currentPizza.getCrust() == null) {
            statusLabel.setText("Error: Crust not set. Please select a valid pizza style.");
            return;
        }
        currentPizza.setSize(pizzaSizeComboBox.getValue());
        if (currentPizza instanceof BuildYourOwn) {
            ((BuildYourOwn) currentPizza).setToppings(new ArrayList<>(selectedToppingsListView.getItems()));
        }
        orderManager.addPizzaToCurrentOrder(currentPizza);
        clearButtonClicked();
        statusLabel.setText("Pizza added to order.");
    }

    /**
     * Clears the current selection and resets the UI components.
     */
    @FXML
    public void clearButtonClicked() {
        pizzaTypeComboBox.getSelectionModel().clearSelection();
        pizzaSizeComboBox.getSelectionModel().clearSelection();
        selectedToppingsListView.getItems().clear();
        availableToppingsListView.getItems().clear();
        availableToppingsListView.getItems().addAll(Topping.values());
        availableToppingsListView.setDisable(true);
        addToppingButton.setDisable(true);
        removeToppingButton.setDisable(true);
        currentPizza = null;
        PizzaTotalLabel.setText("Pizza Total: $0.00");
        pizzaImageView.setImage(null);  // Reset image view to initial state
    }

    /**
     * Adds the selected topping to the pizza and updates the price.
     */
    @FXML
    private void onAddToppingClick() {
        Topping selectedTopping = availableToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null && selectedToppingsListView.getItems().size() < 7) {
            selectedToppingsListView.getItems().add(selectedTopping);
            availableToppingsListView.getItems().remove(selectedTopping);
            if (selectedToppingsListView.getItems().size() == 7) {
                addToppingButton.setDisable(true);
            }
            updatePizzaPrice();
        }
    }

    /**
     * Removes the selected topping from the pizza and updates the price.
     */
    @FXML
    private void onRemoveToppingClick() {
        Topping selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            selectedToppingsListView.getItems().remove(selectedTopping);
            availableToppingsListView.getItems().add(selectedTopping);
            if (selectedToppingsListView.getItems().size() < 7) {
                addToppingButton.setDisable(false);
            }
            updatePizzaPrice();
        }
    }

    /**
     * Sets the reference to the main application.
     * @param mainApp The main application instance.
     */
    @FXML
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Navigates to the Order Details view.
     */
    @FXML
    public void onViewOrderDetailsButtonClicked() {
        if (mainApp != null) {
            mainApp.showOrderDetailView();
        }
    }

    /**
     * Handles the selection of pizza type and updates the current pizza instance.
     * @param pizzaType The selected pizza type.
     */
    private void onPizzaTypeSelected(String pizzaType) {
        if (pizzaType == null || currentFactory == null) {
            return;
        }
        selectedToppingsListView.getItems().clear();
        switch (pizzaType) {
            case "Deluxe":
                currentPizza = currentFactory.createDeluxe();
                selectedToppingsListView.getItems().addAll(currentPizza.getToppings());
                disableToppingsSelection();
                break;
            case "BBQ Chicken":
                currentPizza = currentFactory.createBBQChicken();
                selectedToppingsListView.getItems().addAll(currentPizza.getToppings());
                disableToppingsSelection();
                break;
            case "Meatzza":
                currentPizza = currentFactory.createMeatzza();
                selectedToppingsListView.getItems().addAll(currentPizza.getToppings());
                disableToppingsSelection();
                break;
            case "Build Your Own":
                currentPizza = currentFactory.createBuildYourOwn();
                enableToppingsSelection();
                break;
            default:
                currentPizza = null;
                break;
        }
        updatePizzaPrice();
    }

    /**
     * Handles the selection of pizza style and sets the current factory accordingly.
     * @param style The selected pizza style.
     */
    private void onPizzaStyleSelected(String style) {
        switch (style) {
            case "New York Style":
                currentFactory = new NYPizza();
                break;
            case "Chicago Style":
                currentFactory = new ChicagoPizza();
                break;
        }
        if (currentPizza == null && selectedType != null) {
            onPizzaTypeSelected(selectedType);
        }
    }

    /**
     * Disables the topping selection UI components.
     */
    private void disableToppingsSelection() {
        availableToppingsListView.setDisable(true);
        addToppingButton.setDisable(true);
        removeToppingButton.setDisable(true);
    }

    /**
     * Enables the topping selection UI components.
     */
    private void enableToppingsSelection() {
        availableToppingsListView.setDisable(false);
        addToppingButton.setDisable(false);
        removeToppingButton.setDisable(false);
    }

    /**
     * Updates the displayed pizza price based on the selected size and toppings.
     */
    private void updatePizzaPrice() {
        if (currentPizza == null || pizzaSizeComboBox.getValue() == null) {
            PizzaTotalLabel.setText("Pizza Total: $0.00"); // Default if no pizza or size selected
            return; }
        Size selectedSize = pizzaSizeComboBox.getValue();
        currentPizza.setSize(selectedSize); // Ensure size is set
        double totalPrice = currentPizza.price();
        if (currentPizza instanceof BuildYourOwn) {
            int toppingCount = selectedToppingsListView.getItems().size();
            totalPrice += toppingCount * BuildYourOwn.getToppingCost(); }
        PizzaTotalLabel.setText("Pizza Total: $" + String.format("%.2f", totalPrice));
    }

    /**
     * Updates the displayed image based on the selected pizza style and type.
     */
    private void updatePizzaImage() {
        if (selectedStyle == null || selectedType == null) return;
        String imagePath = "/images/" + selectedStyle.toLowerCase().replace(" ", "_") + "_" + selectedType.toLowerCase().replace(" ", "_") + ".png";
        Image pizzaImage = new Image(getClass().getResourceAsStream(imagePath));
        pizzaImageView.setImage(pizzaImage);
    }
}
