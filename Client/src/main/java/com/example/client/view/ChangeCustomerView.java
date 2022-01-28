package com.example.client.view;

import com.example.client.reference.ReferenceSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeCustomerView {

    private final Stage stage;

    @FXML
    private Button addCustomer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    ReferenceSystem department;
    int customerId;

    public ChangeCustomerView(ReferenceSystem department, int customerID){
        this.department = department;
        this.customerId = customerID;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Change Customer");
            addCustomer.setText("Change");
            fullName.setText(department.getCustomerFromID(customerID).getName());
            telephone.setText(department.getCustomerFromID(customerID).getPhoneNumber());
            address.setText(department.getCustomerFromID(customerID).getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage.showAndWait();
    }
/*
    @FXML
    private void initialize() {
        addCustomer.setOnAction(event -> onChangeCustomerButtonClick());
    }

    @FXML
    protected void onChangeCustomerButtonClick() {
        controller.changeCustomerClick(department, customerId, fullName.getText(), telephone.getText(), address.getText());
        stage.close();
    }


 */
}
