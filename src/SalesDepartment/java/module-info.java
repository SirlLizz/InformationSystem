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

    opens com.example.informationsystem to javafx.fxml;
    exports com.example.informationsystem;
    exports com.example.informationsystem.department;
    opens com.example.informationsystem.department to javafx.fxml;
}