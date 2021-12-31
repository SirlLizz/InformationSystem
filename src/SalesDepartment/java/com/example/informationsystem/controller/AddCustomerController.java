package com.example.informationsystem.controller;

import com.example.informationsystem.reference.ReferenceSystem;

public class AddCustomerController {
    public void addCustomerClick(ReferenceSystem department, String fullName, String telephone, String address){
        department.addCustomer(fullName, telephone, address);
    }
}
