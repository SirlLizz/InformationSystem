module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires Shared;

    exports com.example.client.controller;
    opens com.example.client.controller to javafx.fxml;
    exports com.example.client.view;
    opens com.example.client.view to javafx.fxml;

}