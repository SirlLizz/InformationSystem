package com.example.client.reference;

import com.example.client.model.Customer;
import com.example.client.model.CustomerList;
import com.example.client.model.Order;
import com.example.client.model.OrderList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReferenceSystem{
    private CustomerList customers;
    private OrderList orders;

    public ReferenceSystem(){
        customers = new CustomerList();
        orders = new OrderList();
    }

    public CustomerList getCustomerList(){
        return customers;
    }

    public List<Customer> getCustomers(){
        return customers.getCustomers();
    }

    public OrderList getOrders(){
        return orders;
    }

    public void setCustomers(CustomerList newCustomers){
        customers = newCustomers;
    }

    public void setOrders(OrderList newOrders){
        orders = newOrders;
    }

    public int checkCustomer(Customer customer){
        int k = -1;
        for (int i = 0; i< customers.size();i++){
            if((customers.get(i).getName().equals(customer.getName()))&&
                    (customers.get(i).getAddress().equals(customer.getAddress()))&&
                    (customers.get(i).getPhoneNumber().equals(customer.getPhoneNumber()))){
                k=i;
            }
        }
        return k;
    }

    public int checkOrder(Order order){
        int k = -1;
        for (int i = 0; i< orders.size();i++){
            if((orders.get(i).getOrderDate().equals(order.getOrderDate()))&&
                    (orders.get(i).getOrderPrice() == order.getOrderPrice())&&
                    (orders.get(i).getCustomer().getAddress().equals(order.getCustomer().getAddress()))&&
                    (orders.get(i).getCustomer().getPhoneNumber().equals(order.getCustomer().getPhoneNumber()))&&
                    (orders.get(i).getCustomer().getName().equals(order.getCustomer().getName()))){
                k = i;
            }
        }
        return k;
    }

    public void addCustomer(String name, String phoneNumber, String address){
        customers.add(new Customer(name, phoneNumber, address));
    }

    public Customer getCustomerFromID(int customerID){
        for (int i = 0; i<customers.size();i++){
            if(customers.get(i).getCustomerID() == customerID){
                return customers.get(i);
            }
        }
        return null;
    }

    public void removeCustomer(int customerID){
        for(int i =0; i< customers.size();i++){
            if(customers.get(i).getCustomerID() == customerID){
                for(int j =0; j< orders.size();j++){
                    if(orders.get(j).getCustomer().getCustomerID() == customerID){
                        orders.remove(j);
                        j--;
                    }
                }
                customers.remove(i);
            }
        }
    }

    public void changeCustomerInformation(int customerID, String name, String phoneNumber, String address){
        for(int i =0; i< customers.size();i++){
            if(customers.get(i).getCustomerID() == customerID){
                customers.get(i).setName(name);
                customers.get(i).setPhoneNumber(phoneNumber);
                customers.get(i).setAddress(address);
            }
        }

    }

    public String browseCustomerInformation(){
        String out = "";
        for(int i = 0; i<customers.size();i++){
            out += customers.get(i).toString() + "\n";
        }
        return out;
    }

    public void addOrder(Customer customer, LocalDate date, double orderPrice){
        orders.add(new Order(customer, date, orderPrice));
    }

    public Order getOrderFromID(int orderID){
        for (int i = 0; i<orders.size();i++){
            if(orders.get(i).getOrderID() == orderID){
                return orders.get(i);
            }
        }
        return null;
    }

    public void removeOrder(int orderID){
        for(int i =0; i< orders.size();i++){
            if(orders.get(i).getOrderID() == orderID){
                orders.remove(i);
            }
        }
    }

    public void changeOrderInformation(int orderID, Customer customer, LocalDate orderDate, double orderPrice){
        orders.get(orderID).setCustomer(customer);
        orders.get(orderID).setOrderDate(orderDate);
        orders.get(orderID).setOrderPrice(orderPrice);
    }

    public String browseOrderInformation(){
        String out = "";
        for(int i = 0; i<orders.size();i++){
            out += orders.get(i).toString() + "\n";
        }
        return out;
    }
    
}
