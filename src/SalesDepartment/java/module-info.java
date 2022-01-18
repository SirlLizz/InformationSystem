module com.example.informationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.xml.bind;


    opens com.example.informationsystem to javafx.fxml;
    exports com.example.informationsystem;
    exports com.example.informationsystem.model;
    opens com.example.informationsystem.model to javafx.fxml, java.xml.bind;
    exports com.example.informationsystem.view;
    opens com.example.informationsystem.view to javafx.fxml;
    exports com.example.informationsystem.controller;
    opens com.example.informationsystem.controller to javafx.fxml;
    exports com.example.informationsystem.reference;
    opens com.example.informationsystem.reference to java.xml.bind, javafx.fxml;
}