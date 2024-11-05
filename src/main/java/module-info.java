module com.example.project4 {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.project4.gui;
    opens com.example.project4.gui to javafx.fxml;

    exports com.example.project4.management;
    opens com.example.project4.management to javafx.fxml;

    exports com.example.project4.pizzeria;
    opens com.example.project4.pizzeria to javafx.fxml;
}
