package com.example.client.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "orderList")
@XmlRootElement
public class OrderList {
    private List<Order> orders;

    public  OrderList(){
        orders = new ArrayList<>();
    }

    public  OrderList(List<Order> newOrders){
        orders = newOrders;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int size(){
        return orders.size();
    }

    public Order get(int i){
        return orders.get(i);
    }

    public void add(Order order){
        orders.add(order);
    }

    public void remove(int i){
        orders.remove(i);
    }
}
