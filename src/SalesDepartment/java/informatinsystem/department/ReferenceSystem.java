package informatinsystem.department;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReferenceSystem{
    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public ReferenceSystem(){
        customers = new ArrayList<Customer>();
        orders = new ArrayList<Order>();
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

    public boolean checkCustomer(Customer customer){
        int k = 0;
        for (int i = 0; i< customers.size();i++){
            if((customers.get(i).getName().equals(customer.getName()))&&
                    (customers.get(i).getAddress().equals(customer.getAddress()))&&
                    (customers.get(i).getPhoneNumber().equals(customer.getPhoneNumber()))){
                k++;
            }
        }
        if(k==0){
            return true;
        }
        return false;
    }

    public boolean checkOrder(Order order){
        int k = 0;
        for (int i = 0; i< orders.size();i++){
            if((orders.get(i).getOrderDate().equals(order.getOrderDate()))&&
                    (orders.get(i).getOrderPrice() == order.getOrderPrice())&&
                    (orders.get(i).getCustomer().getAddress().equals(order.getCustomer().getAddress()))&&
                    (orders.get(i).getCustomer().getPhoneNumber().equals(order.getCustomer().getPhoneNumber()))&&
                    (orders.get(i).getCustomer().getName().equals(order.getCustomer().getName()))){
                k++;
            }
        }
        if(k==0){
            return true;
        }
        return false;
    }

    public void addCustomer(String name, String phoneNumber, String address){
        customers.add(new Customer(name, phoneNumber, address));
    }

    public void addOrder(Customer customer, LocalDate date, double orderPrice){
        orders.add(new Order(customer, date, orderPrice));
    }

    public void removeCustomer(int customerID){
        customers.remove(customerID);
    }

    public void removeOrder(int orderID){
        orders.remove(orderID);
    }

    public void changeOrderInformation(int orderID, Customer customer, LocalDate orderDate, double orderPrice){
        orders.get(orderID).setCustomer(customer);
        orders.get(orderID).setOrderDate(orderDate);
        orders.get(orderID).setOrderPrice(orderPrice);
    }

    public void changeCustomerInformation(int customerID, String name, String phoneNumber, String address){
        customers.get(customerID).setName(name);
        customers.get(customerID).setPhoneNumber(phoneNumber);
        customers.get(customerID).setAddress(address);
    }

    public void clearCustomers(){
        customers = new ArrayList<>();
    }

    public void clearOrders(){
        orders = new ArrayList<>();
    }

    public String browseCustomerInformation(){
        String out = "";
        for(int i = 0; i<customers.size();i++){
            out += customers.get(i).toString() + "\n";
        }
        return out;
    }

    public String browseOrderInformation(){
        String out = "";
        for(int i = 0; i<orders.size();i++){
            out += orders.get(i).toString() + "\n";
        }
        return out;
    }
}
