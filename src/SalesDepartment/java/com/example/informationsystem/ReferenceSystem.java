package com.example.informationsystem;


import com.example.informationsystem.department.Customer;
import com.example.informationsystem.department.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReferenceSystem {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    ReferenceSystem(){
        customers = new ArrayList<Customer>();
        orders = new ArrayList<Order>();
    }

    public static List<Customer> getCustomers(){
        return customers;
    }

    public static void addCustomer(String name, String phoneNumber, String address){
        customers.add(new Customer(name, phoneNumber, address));
    }

    public void removeCustomer(int customerID){
        customers.remove(customerID);
    }

    public void changeCustomerInformation(int customerID, String name, String phoneNumber, String address){
        customers.get(customerID).setName(name);
        customers.get(customerID).setPhoneNumber(phoneNumber);
        customers.get(customerID).setAddress(address);
    }

    public static String browseCustomerInformation(){
        String out = "";
        for(int i = 0; i<customers.size();i++){
            out += customers.get(i).toString() + "\n";
        }
        return out;
    }

    public static void addOrder(Customer customer, LocalDate date, double orderPrice){
        orders.add(new Order(customer, date, orderPrice));
    }

    public void removeOrder(int orderID){
        orders.remove(orderID);
    }

    public void changeOrderInformation(int orderID, Customer customer, LocalDate orderDate, double orderPrice){
        orders.get(orderID).setCustomer(customer);
        orders.get(orderID).setOrderDate(orderDate);
        orders.get(orderID).setOrderPrice(orderPrice);
    }

    public static String browseOrderInformation(){
        String out = "";
        for(int i = 0; i<orders.size();i++){
            out += orders.get(i).toString() + "\n";
        }
        return out;
    }
    
}
