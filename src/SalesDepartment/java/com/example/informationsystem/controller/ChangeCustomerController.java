package com.example.informationsystem.controller;

import com.example.informationsystem.reference.ReferenceSystem;

public class ChangeCustomerController {

    ReferenceSystem department;

    public ChangeCustomerController(ReferenceSystem department){
        this.department = department;
    }

    public void changeCustomerClick(ReferenceSystem department, int customerID, String fullName, String telephone, String address){
        department.changeCustomerInformation(customerID, fullName, telephone, address);
    }
}
