package com.example.informationsystem.model;

import com.example.informationsystem.model.Customer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "customerList")
@XmlRootElement
public class CustomerList {
    private List<Customer> customers;

    public CustomerList(){
        customers = new ArrayList<>();
    }

    public CustomerList(List<Customer> newCustomers){
        customers = newCustomers;
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public void setCustomers(List<Customer> newCustomers) {
        this.customers = newCustomers;
    }
}
