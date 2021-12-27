package com.example.informationsystem.controller;

import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.view.AddCustomerView;

public class AddCustomerController {
    public void addCustomerClick(ReferenceSystem department, String fullName, String telephone, String address){
        department.addCustomer(fullName, telephone, address);
    }
}
