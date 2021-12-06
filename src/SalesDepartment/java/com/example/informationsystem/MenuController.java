package com.example.informationsystem;

import com.example.informationsystem.AddCustomerController;
import com.example.informationsystem.AddOrderController;
import com.example.informationsystem.ReferenceSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class MenuController {

    @FXML
    private static TextArea area ;

    @FXML
    protected void onCustomerClick() throws IOException {
        AddCustomerController.createAddCustomer();
    }

    @FXML
    protected void onOrderClick() throws IOException {
        AddOrderController.createAddOrder();
    }

}