package com.example.informationsystem.controller;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.reference.Serialization;
import com.example.informationsystem.view.AddCustomerView;
import com.example.informationsystem.view.AddOrderView;
import com.example.informationsystem.view.MenuView;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuController{

    Serialization serialization = new Serialization();

    public void addCustomerClick(ReferenceSystem department){
        AddCustomerView addCust = new AddCustomerView(department);
        addCust.showStage();
    }

    public void addOrderClick(ReferenceSystem department){
        AddOrderView addOrd = new AddOrderView(department);
        addOrd.showStage();
    }

    public void serializeOrder(ReferenceSystem department){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Order", "*.ord"));
        Stage stage = new Stage();
        File path = chooser.showSaveDialog(stage);
        serialization.serialize(department.getOrders(), path);
    }

    public void openOrder(ReferenceSystem department){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Order", "*.ord"));
        Stage stage = new Stage();
        File path = chooser.showOpenDialog(stage);
        List<Order> orders = serialization.deserializeOrder(path);
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i< orders.size();i++){
            customers.add(orders.get(i).getCustomer());
        }
        department.setCustomers(customers);
        department.setOrders(orders);
    }

    public void addOpenOrder(ReferenceSystem department){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Order", "*.ord"));
        Stage stage = new Stage();
        File path = chooser.showOpenDialog(stage);
        List<Order> orders = serialization.deserializeOrder(path);
        for (int i = 0; i< orders.size();i++){
            if(department.checkOrder(orders.get(i))){
                if(department.checkCustomer(orders.get(i).getCustomer())){
                    department.addCustomer(orders.get(i).getCustomer().getName(),orders.get(i).getCustomer().getPhoneNumber(),orders.get(i).getCustomer().getAddress());
                }
                department.addOrder(orders.get(i).getCustomer(),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
            }
        }
    }

    public void serializeCustomer(ReferenceSystem department) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Customer", "*.cust"));
        Stage stage = new Stage();
        File path = chooser.showSaveDialog(stage);
        serialization.serialize(department.getCustomers(), path);
    }

    public void openCustomer(ReferenceSystem department) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Customer", "*.cust"));
        Stage stage = new Stage();
        File path = chooser.showOpenDialog(stage);
        department.setCustomers(serialization.deserializeCustomer(path));
    }

    public void addOpenCustomer(ReferenceSystem department) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Customer", "*.cust"));
        Stage stage = new Stage();
        File path = chooser.showOpenDialog(stage);
        List<Customer> customers = serialization.deserializeCustomer(path);
        for (int i = 0; i< customers.size();i++){
            if(department.checkCustomer(customers.get(i))){
                department.addCustomer(customers.get(i).getName(),customers.get(i).getPhoneNumber(),customers.get(i).getAddress());
            }
        }
    }
}
