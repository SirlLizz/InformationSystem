module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;

    opens com.example.client.model to java.xml.bind;
    exports com.example.client.model;
    opens com.example.client.transport to java.xml.bind;
    exports com.example.client.transport;
    exports com.example.client.controller;
    opens com.example.client.controller to javafx.fxml;
    exports com.example.client.view;
    opens com.example.client.view to javafx.fxml;
}