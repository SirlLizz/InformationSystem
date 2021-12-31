package com.example.informationsystem.controller;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.view.AddCustomerView;
import com.example.informationsystem.view.ChangeCustomerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChangeOrderController {

    public void changeCustomerClick(ReferenceSystem department, int customerID){
        ChangeCustomerView changeCust = new ChangeCustomerView(department, customerID);
        changeCust.showStage();
    }

    public void changeOrderClick(ReferenceSystem department, int orderID, Customer customer, LocalDate date, double price){
        department.changeOrderInformation(orderID, customer, date, price);
    }

    public Customer actionComboBox(ReferenceSystem department, String comboValue){
        Customer customer = null;
        for (int i = 0; i < department.getCustomers().size(); i++) {
            if (department.getCustomers().get(i).getName().equals(comboValue)) {
                customer = department.getCustomers().get(i);
            }
        }
        return customer;
    }
    public List<String> showComboBox(ReferenceSystem department){
        List<String> names = new ArrayList<>();
        for (int i = 0; i < department.getCustomers().size(); i++){
            names.add(department.getCustomers().get(i).getName());
        }
        return names;
    }

    public boolean checkPriceToDouble(String price) {
        try {
            Double priceDouble = Double.parseDouble(price);
            return true;
        } catch(NumberFormatException e){
            System.out.println("Price is incorrect!");
        }
        return false;
    }

    public boolean checkCustomer(Customer customer) {
        if(customer == null){
            return false;
        }
        return true;
    }
}
