package com.example.informationsystem.controller;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import com.example.informationsystem.reference.Find;
import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.reference.Serialization;
import com.example.informationsystem.view.AddCustomerView;
import com.example.informationsystem.view.AddOrderView;
import com.example.informationsystem.view.ChangeCustomerView;
import com.example.informationsystem.view.ChangeOrderView;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuController{

    Serialization serialization;
    Find find;
    AddOrderController addOrderController;
    AddCustomerController addCustomerController;
    ChangeCustomerController changeCustomerController;
    ChangeOrderController changeOrderController;

    public MenuController(Serialization serialization, Find find, AddOrderController addOrderController, AddCustomerController addCustomerController, ChangeOrderController changeOrderController, ChangeCustomerController changeCustomerController){
        this.serialization = serialization;
        this.find = find;
        this.addOrderController = addOrderController;
        this.addCustomerController = addCustomerController;
        this.changeCustomerController = changeCustomerController;
        this.changeOrderController = changeOrderController;
    }

    public void addCustomerClick(ReferenceSystem department){
        AddCustomerView addCust = new AddCustomerView(department, addCustomerController);
        addCust.showStage();
    }

    public void deleteCustomerClick(ReferenceSystem department, int customerID){
        department.removeCustomer(customerID);
    }

    public void changeCustomerClick(ReferenceSystem department, int customerID){
        ChangeCustomerView changeCust = new ChangeCustomerView(department, customerID, changeCustomerController);
        changeCust.showStage();
    }

    public void addOrderClick(ReferenceSystem department){
        AddOrderView addOrd = new AddOrderView(department, addOrderController);
        addOrd.showStage();
    }

    public void deleteOrderClick(ReferenceSystem department, int orderID){
        department.removeOrder(orderID);
    }

    public void changeOrderClick(ReferenceSystem department, int orderID){
        ChangeOrderView changeOrd = new ChangeOrderView(department, orderID, changeOrderController);
        changeOrd.showStage();
    }

    public void serializeOrder(ReferenceSystem department, File file) {
        serialization.serializeOrder(department.getOrders(), file);
    }

    public void openOrder(ReferenceSystem department, File file){
        List<Order> orders = serialization.deserializeOrder(file);
        department.setCustomers(new ArrayList<>());
        department.setOrders(new ArrayList<>());
        Customer.setNextID(0);
        Order.setNextID(0);
        for (int i = 0; i< orders.size();i++){
            if(department.checkCustomer(orders.get(i).getCustomer())==-1) {
                department.addCustomer(orders.get(i).getCustomer().getName(), orders.get(i).getCustomer().getPhoneNumber(), orders.get(i).getCustomer().getAddress());
            }
            department.addOrder(department.getCustomers().get(department.getCustomers().size()-1),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
        }
    }

    public void addOpenOrder(ReferenceSystem department, File file){
        List<Order> orders = serialization.deserializeOrder(file);
        for (int i = 0; i< orders.size();i++){
            if(department.checkOrder(orders.get(i)) == -1){
                if(department.checkCustomer(orders.get(i).getCustomer())==-1){
                    department.addCustomer(orders.get(i).getCustomer().getName(),orders.get(i).getCustomer().getPhoneNumber(),orders.get(i).getCustomer().getAddress());
                    department.addOrder(department.getCustomers().get(department.getCustomers().size()-1),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
                }else{
                    department.addOrder(department.getCustomers().get(department.checkCustomer(orders.get(i).getCustomer())),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
                }
            }
        }
    }

    public void serializeCustomer(ReferenceSystem department, File file) {
        serialization.serializeCustomer(department.getCustomers(), file);
    }

    public void openCustomer(ReferenceSystem department, File file) {
        department.setCustomers(serialization.deserializeCustomer(file));
    }

    public void addOpenCustomer(ReferenceSystem department, File file) {
        List<Customer> customers = serialization.deserializeCustomer(file);
        for (int i = 0; i< customers.size();i++){
            if(department.checkCustomer(customers.get(i)) == -1){
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
