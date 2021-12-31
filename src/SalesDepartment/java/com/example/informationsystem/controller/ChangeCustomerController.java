package com.example.informationsystem.controller;

import com.example.informationsystem.reference.ReferenceSystem;

public class ChangeCustomerController {
    public void changeCustomerClick(ReferenceSystem department, int customerID, String fullName, String telephone, String address){
        department.changeCustomerInformation(customerID, fullName, telephone, address);
    }
}
