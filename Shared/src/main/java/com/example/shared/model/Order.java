package com.example.shared.model;

import com.example.shared.reference.LocalDateAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@XmlRootElement
@XmlType(name = "order")
public class Order implements Serializable{

    @XmlAttribute(name = "ID")
    private final UUID orderID;
    private Customer customer;
    private LocalDate orderDate;
    private double orderPrice;

    public Order(){
        this.orderID = UUID.randomUUID();
        this.customer = null;
        this.orderDate = null;
        this.orderPrice = 0;
    }

    public Order(Customer customer, LocalDate orderDate, double orderPrice){
        this.orderID = UUID.randomUUID();
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

    public String getOrderID(){
        return orderID.toString();
    }

    public String toString(){
        return "ID: " + orderID + "; model.Customer: (" + customer.toString() + "); Date: " + orderDate + "; Price: " + orderPrice + "\n";
    }
}
