package com.example.informationsystem.controller;

import com.example.informationsystem.department.ReferenceSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerController {

    @FXML
    private Button addCustomer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    public static void createAddCustomer() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AddCustomerController.class.getResource("AddCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAddCustomerButtonClick() {
        ReferenceSystem.addCustomer(fullName.getText(), telephone.getText(), address.getText());
        System.out.println(ReferenceSystem.browseCustomerInformation());
        Stage stage = (Stage) addCustomer.getScene().getWindow();
        stage.close();
    }
}
