module com.example.pharmacymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pharmacymanagement to javafx.fxml;
    exports com.example.pharmacymanagement;
}