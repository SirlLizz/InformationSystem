package controller.view;

import com.example.shared.model.Customer;
import com.example.shared.model.Order;
import com.example.shared.reference.ReferenceSystem;
import reference.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuController{

    ReferenceSystem department;
    Find find;
    AddOrderController addOrderController;
    AddCustomerController addCustomerController;
    ChangeCustomerController changeCustomerController;
    ChangeOrderController changeOrderController;

    public MenuController(ReferenceSystem department, Find find, AddOrderController addOrderController, AddCustomerController addCustomerController, ChangeOrderController changeOrderController, ChangeCustomerController changeCustomerController){
        this.department = department;
        this.find = find;
        this.addOrderController = addOrderController;
        this.addCustomerController = addCustomerController;
        this.changeCustomerController = changeCustomerController;
        this.changeOrderController = changeOrderController;
    }

    public void deleteCustomerClick(String customerID){
        department.removeCustomer(customerID);
    }

    public void deleteOrderClick(String orderID){
        department.removeOrder(orderID);
    }

    public void openOrder(String[][] matrixOrder){
        List<Order> orders = new ArrayList<>();
        for(int i =0;i< matrixOrder.length;i++){
            orders.add(new Order(new Customer(matrixOrder[i][0],matrixOrder[i][1], matrixOrder[i][2]), LocalDate.parse(matrixOrder[i][3]), Double.parseDouble(matrixOrder[i][4])));
        }
        department.setCustomers(new ArrayList<>());
        department.setOrders(new ArrayList<>());
        for (int i = 0; i< orders.size();i++){
            if(department.checkCustomer(orders.get(i).getCustomer())==-1) {
                department.addCustomer(orders.get(i).getCustomer().getName(), orders.get(i).getCustomer().getPhoneNumber(), orders.get(i).getCustomer().getAddress());
            }
            department.addOrder(department.getCustomers().get(department.getCustomers().size()-1),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
        }
    }

    public void addOpenOrder(String[][] matrixOrder){
        List<Order> orders = new ArrayList<>();
        for(int i =0;i< matrixOrder.length;i++){
            orders.add(new Order(new Customer(matrixOrder[i][0],matrixOrder[i][1], matrixOrder[i][2]), LocalDate.parse(matrixOrder[i][3]), Double.parseDouble(matrixOrder[i][4])));
        }
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

    public void openCustomer(String[][] matrixCustomer) {
        List<Customer> customers = new ArrayList<>();
        for(int i =0;i< matrixCustomer.length;i++){
            customers.add(new Customer(matrixCustomer[i][0],matrixCustomer[i][1], matrixCustomer[i][2]));
        }
        department.setCustomers(customers);
    }

    public void addOpenCustomer(String[][] matrixCustomer) {
        List<Customer> customers = new ArrayList<>();
        for(int i =0;i< matrixCustomer.length;i++){
            customers.add(new Customer(matrixCustomer[i][0],matrixCustomer[i][1], matrixCustomer[i][2]));
        }
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
        Comparator<Customer> comparator = Comparator.comparing(Customer::getName);
        department.getCustomers().sort(comparator);
    }

    public void sortCustomerToLow() {
        Comparator<Customer> comparator = Comparator.comparing(Customer::getName).reversed();
        department.getCustomers().sort(comparator);
    }

    public void sortOrderToHigh() {
        Comparator<Order> comparator = Comparator.comparing(Order::getCustomer);
        department.getOrders().sort(comparator);
    }

    public void sortOrderToLow() {
        Comparator<Order> comparator = Comparator.comparing(Order::getCustomer).reversed();
        department.getOrders().sort(comparator);
    }

}
