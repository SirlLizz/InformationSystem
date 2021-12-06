package com.example.informationsystem.department;

import com.example.informationsystem.department.Customer;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    static private int nextID = 0;

    private int orderID;
    private Customer customer;
    private LocalDate orderDate;
    private double orderPrice;

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer newCustomer){
        customer = newCustomer;
    }

    public LocalDate getOrderDate(){
        return orderDate;
    }

    public void setOrderDate(LocalDate newOrderDate)
    {
        orderDate = newOrderDate;
    }

    public double getOrderPrice(){
        return orderPrice;
    }

    public void setOrderPrice(double newOrderPrice){
        orderPrice = newOrderPrice;
    }

    public Order(Customer customer, LocalDate orderDate, double orderPrice){
        orderID = nextID++;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
    }

    public String toString(){
        return "ID: " + orderID + "; Customer: (" + customer.toString() + "); Date: " + orderDate + "; Price: " + orderPrice;
    }
}
