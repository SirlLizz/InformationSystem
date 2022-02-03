package controller.view;

import model.Customer;
import model.Order;
import reference.Find;
import reference.ReferenceSystem;
import reference.Serialization;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuController{

    ReferenceSystem department;
    Serialization serialization;
    Find find;
    AddOrderController addOrderController;
    AddCustomerController addCustomerController;
    ChangeCustomerController changeCustomerController;
    ChangeOrderController changeOrderController;

    public MenuController(ReferenceSystem department, Serialization serialization, Find find, AddOrderController addOrderController, AddCustomerController addCustomerController, ChangeOrderController changeOrderController, ChangeCustomerController changeCustomerController){
        this.department = department;
        this.serialization = serialization;
        this.find = find;
        this.addOrderController = addOrderController;
        this.addCustomerController = addCustomerController;
        this.changeCustomerController = changeCustomerController;
        this.changeOrderController = changeOrderController;
    }

    public void deleteCustomerClick(int customerID){
        department.removeCustomer(customerID);
    }

    public void deleteOrderClick(int orderID){
        department.removeOrder(orderID);
    }

    public void openOrder(File file){
        List<Order> orders = serialization.deserializeOrder(file);
        department.setCustomers(new ArrayList<>());
        department.setOrders(new ArrayList<>());
        Customer.setNextID(0);
        Order.setNextID(0);
        for (int i = 0; i< orders.size();i++){
            if(department.checkCustomer(orders.get(i).getCustomer())==-1) {
                department.addCustomer(orders.get(i).getCustomer().getName(), orders.get(i).getCustomer().getPhoneNumber(), orders.get(i).getCustomer().getAddress());
            }
            department.addOrder(department.getCustomers().get(department.getCustomers().size()-1),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
        }
    }

    public void addOpenOrder(File file){
        List<Order> orders = serialization.deserializeOrder(file);
        for (int i = 0; i< orders.size();i++){
            if(department.checkOrder(orders.get(i)) == -1){
                if(department.checkCustomer(orders.get(i).getCustomer())==-1){
                    department.addCustomer(orders.get(i).getCustomer().getName(),orders.get(i).getCustomer().getPhoneNumber(),orders.get(i).getCustomer().getAddress());
                    department.addOrder(department.getCustomers().get(department.getCustomers().size()-1),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
                }else{
                    department.addOrder(department.getCustomers().get(department.checkCustomer(orders.get(i).getCustomer())),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
                }
            }
        }
    }

    public void serializeCustomer(File file) {
        serialization.serializeCustomer(department.getCustomers(), file);
    }

    public void openCustomer(File file) {
        department.setCustomers(serialization.deserializeCustomer(file));
    }

    public void addOpenCustomer(File file) {
        List<Customer> customers = serialization.deserializeCustomer(file);
        for (int i = 0; i< customers.size();i++){
            if(department.checkCustomer(customers.get(i)) == -1){
                department.addCustomer(customers.get(i).getName(),customers.get(i).getPhoneNumber(),customers.get(i).getAddress());
            }
        }
    }

    public List<Order> findDate(String pattern) {
        return find.findDate(pattern);
    }

    public List<Order> findPrice(String pattern) {
        return find.findPrice(pattern);
    }

    public List<Order> findFullName(String pattern) {
        return find.findFullName(pattern);
    }

    public List<Order> findNumber(String pattern) {
        return find.findNumber(pattern);
    }

    public List<Order> findAddress(String pattern) {
        return find.findAddress(pattern);
    }

    public void sortCustomerToHigh() {
        Comparator<Customer> comparator = Comparator.comparing(Customer::getCustomerID);
        department.getCustomers().sort(comparator);
    }

    public void sortCustomerToLow() {
        Comparator<Customer> comparator = Comparator.comparing(Customer::getCustomerID).reversed();
        department.getCustomers().sort(comparator);
    }

    public void sortOrderToHigh() {
        Comparator<Order> comparator = Comparator.comparing(Order::getOrderID);
        department.getOrders().sort(comparator);
    }

    public void sortOrderToLow() {
        Comparator<Order> comparator = Comparator.comparing(Order::getOrderID).reversed();
        department.getOrders().sort(comparator);
    }

}
