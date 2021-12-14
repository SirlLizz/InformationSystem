package com.example.informationsystem.controller;

import com.example.informationsystem.department.ReferenceSystem;
import com.example.informationsystem.Serialization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public void onSerializeCustomerClick(ActionEvent actionEvent){
        Serialization.serializeCustomer(ReferenceSystem.getCustomers());
    }

    public void onOpenCustomerClick(ActionEvent actionEvent) throws ClassNotFoundException {
        ReferenceSystem.setCustomers(Serialization.deserializeCustomer());
    }

    public void onSerializeOrderClick(ActionEvent actionEvent){
        Serialization.serializeOrder(ReferenceSystem.getOrders());
    }

    public void onOpenOrderClick(ActionEvent actionEvent) throws ClassNotFoundException {
        ReferenceSystem.setOrders(Serialization.deserializeOrder());
    }
}