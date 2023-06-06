module com.example.nuclear {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nuclear to javafx.fxml;
    exports com.example.nuclear;
    exports com.example.nuclear.model;
    opens com.example.nuclear.model to javafx.fxml;
}