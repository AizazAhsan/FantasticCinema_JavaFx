module com.example.javafundamentals_endassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafundamentals_endassignment to javafx.fxml;
    exports com.example.javafundamentals_endassignment;
    exports database;
    opens database to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
}