package com.example.informationsystem.model;

import java.io.Serializable;

public class Customer implements Serializable {
    static private int nextID = 0;

    private final int customerID;
    private String name;
    private String phoneNumber;
    private String address;

    public Customer(String name, String phoneNumber, String address){
        customerID = nextID++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String newAddress){
        address = newAddress;
    }

    public int getCustomerID(){
        return customerID;
    }

    public static void setNextID(int newID){
        nextID = newID;
    }

    public String toString(){
        return "ID: " + customerID + "; Name: " + name + "; Phone: " + phoneNumber + "; Address: " + address;
    }

}
