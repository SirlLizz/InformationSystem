package com.example.informationsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class MenuController {

    @FXML
    public TextArea area;

    @FXML
    protected void onCustomerClick() throws IOException {
        AddCustomerController.createAddCustomer();


    }

    @FXML
    protected void onOrderClick() throws IOException {
        AddOrderController.createAddOrder();
    }

    public void setArea(String str){
        area.setText(str);
    }

}