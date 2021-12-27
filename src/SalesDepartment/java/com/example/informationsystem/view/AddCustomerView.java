package com.example.informationsystem.view;

import com.example.informationsystem.controller.AddCustomerController;
import com.example.informationsystem.reference.ReferenceSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerView {

    private final Stage stage;

    @FXML
    private Button addCustomer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    ReferenceSystem department = null;
    AddCustomerController controller = new AddCustomerController();

    public AddCustomerView(ReferenceSystem department){
        this.department = department;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add Customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage.showAndWait();
    }

    @FXML
    private void initialize() {
        addCustomer.setOnAction(event -> onAddCustomerButtonClick());
    }

    @FXML
    protected void onAddCustomerButtonClick() {
        controller.addCustomerClick(department, fullName.getText(), telephone.getText(), address.getText());
        stage.close();
    }
}
