package com.example.informationsystem.controller;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import com.example.informationsystem.reference.Find;
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
    Find find = new Find();

    public void addCustomerClick(ReferenceSystem department){
        AddCustomerView addCust = new AddCustomerView(department);
        addCust.showStage();
    }

    public void addOrderClick(ReferenceSystem department){
        AddOrderView addOrd = new AddOrderView(department);
        addOrd.showStage();
    }

    public void serializeOrder(ReferenceSystem department, File file){
        serialization.serialize(department.getOrders(), file);
    }

    public void openOrder(ReferenceSystem department, File file){
        List<Order> orders = serialization.deserializeOrder(file);
        List<Customer> customers = new ArrayList<>();
        department.setCustomers(new ArrayList<>());
        department.setOrders(new ArrayList<>());
        for (int i = 0; i< orders.size();i++){
            customers.add(orders.get(i).getCustomer());
            department.addCustomer(orders.get(i).getCustomer().getName(),orders.get(i).getCustomer().getPhoneNumber(),orders.get(i).getCustomer().getAddress());
            department.addOrder(orders.get(i).getCustomer(),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
        }
    }

    public void addOpenOrder(ReferenceSystem department, File file){
        List<Order> orders = serialization.deserializeOrder(file);
        for (int i = 0; i< orders.size();i++){
            if(department.checkOrder(orders.get(i))){
                if(department.checkCustomer(orders.get(i).getCustomer())){
                    department.addCustomer(orders.get(i).getCustomer().getName(),orders.get(i).getCustomer().getPhoneNumber(),orders.get(i).getCustomer().getAddress());
                }
                department.addOrder(orders.get(i).getCustomer(),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
            }
        }
    }

    public void serializeCustomer(ReferenceSystem department, File file) {
        serialization.serialize(department.getCustomers(), file);
    }

    public void openCustomer(ReferenceSystem department, File file) {
        department.setCustomers(serialization.deserializeCustomer(file));
    }

    public void addOpenCustomer(ReferenceSystem department, File file) {
        List<Customer> customers = serialization.deserializeCustomer(file);
        for (int i = 0; i< customers.size();i++){
            if(department.checkCustomer(customers.get(i))){
                department.addCustomer(customers.get(i).getName(),customers.get(i).getPhoneNumber(),customers.get(i).getAddress());
            }
        }
    }

    public List<Order> findDate(ReferenceSystem department, String pattern) {
        return find.findDate(department, pattern);
    }

    public List<Order> findPrice(ReferenceSystem department, String pattern) {
        return find.findPrice(department, pattern);
    }

    public List<Order> findFullName(ReferenceSystem department, String pattern) {
        return find.findFullName(department, pattern);
    }

    public List<Order> findNumber(ReferenceSystem department, String pattern) {
        return find.findNumber(department, pattern);
    }

    public List<Order> findAddress(ReferenceSystem department, String pattern) {
        return find.findAddress(department, pattern);
    }
}
