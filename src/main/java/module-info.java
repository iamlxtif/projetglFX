module com.example.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.dlsc.formsfx;

    opens com.example.main to javafx.fxml;
    exports com.example.main;
}