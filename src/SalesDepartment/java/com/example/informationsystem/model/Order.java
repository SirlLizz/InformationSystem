package com.example.informationsystem.model;

import com.example.informationsystem.reference.LocalDateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlType(name = "order")
public class Order {
    static private int nextID = 0;

    private final int orderID;
    private Customer customer;
    private LocalDate orderDate;
    private double orderPrice;

    public Order(){
        this.orderID = 0;
        this.customer = null;
        this.orderDate = null;
        this.orderPrice = 0;
    }

    public Order(Customer customer, LocalDate orderDate, double orderPrice){
        this.orderID = nextID++;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer newCustomer){
        customer = newCustomer;
    }

    public LocalDate getOrderDate(){
        return orderDate;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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

    public int getOrderID(){
        return orderID;
    }

    public static void setNextID(int newID){
        nextID = newID;
    }

    public String toString(){
        return "ID: " + orderID + "; model.Customer: (" + customer.toString() + "); Date: " + orderDate + "; Price: " + orderPrice + "\n";
    }
}
