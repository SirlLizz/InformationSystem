package reference;

import model.Customer;
import model.Order;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReferenceSystem implements Serializable {
    private List<Customer> customers;
    private List<Order> orders;

    public ReferenceSystem(){
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void setCustomers(List<Customer> newCustomers){
        customers = newCustomers;
    }

    public void setOrders(List<Order> newOrders){
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
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID) {
                return customer;
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
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID) {
                customer.setName(name);
                customer.setPhoneNumber(phoneNumber);
                customer.setAddress(address);
            }
        }
    }

    public String browseCustomerInformation(){
        StringBuilder out = new StringBuilder();
        for (Customer customer : customers) {
            out.append(customer.toString()).append("\n");
        }
        return out.toString();
    }

    public void addOrder(Customer customer, LocalDate date, double orderPrice){
        if(checkCustomer(customer) == -1){
            orders.add(new Order(customer, date, orderPrice));
        }else{
            System.out.println(getCustomerFromID(checkCustomer(customer)));
            orders.add(new Order(customers.get(checkCustomer(customer)), date, orderPrice));
        }
    }

    public Order getOrderFromID(int orderID){
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return order;
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
        if(checkCustomer(customer) == -1){
            getOrderFromID(orderID).setCustomer(customer);
        }else{
            getOrderFromID(orderID).setCustomer(customers.get(checkCustomer(customer)));
        }
        getOrderFromID(orderID).setOrderDate(orderDate);
        getOrderFromID(orderID).setOrderPrice(orderPrice);
    }

    public String browseOrderInformation(){
        StringBuilder out = new StringBuilder();
        for (Order order : orders) {
            out.append(order.toString()).append("\n");
        }
        return out.toString();
    }
    
}
