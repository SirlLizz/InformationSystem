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

    opens informatinsystem to javafx.fxml;
    exports informatinsystem;
    exports informatinsystem.department;
    opens informatinsystem.department to javafx.fxml;
    opens informatinsystem.controller to javafx.fxml;
    exports informatinsystem.view;
    opens informatinsystem.view to javafx.fxml;
    exports informatinsystem.controller;
    exports informatinsystem.view.listener;
    opens informatinsystem.view.listener to javafx.fxml;
}