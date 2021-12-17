package informatinsystem.view.listener;

import informatinsystem.Serialization;
import informatinsystem.department.Customer;
import informatinsystem.department.Order;
import informatinsystem.department.ReferenceSystem;
import informatinsystem.view.AddCustomerView;
import informatinsystem.view.AddOrderView;
import informatinsystem.view.listener.AddCustomerListener;
import informatinsystem.view.listener.AddOrderListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController{

    @FXML
    private TextArea customer;

    @FXML
    private TextArea order;

    private ReferenceSystem department = new ReferenceSystem();

    @FXML
    protected void onCustomerClick(ActionEvent actionEvent){
        new AddCustomerView();
    }

    @FXML
    protected void onOrderClick(){
        new AddOrderView();
    }

    @FXML
    protected void onSerializeCustomerClick(ActionEvent actionEvent){
        Serialization.serializeCustomer(department.getCustomers());
    }

    @FXML
    protected void onOpenCustomerClick(ActionEvent actionEvent) throws ClassNotFoundException {
        department.setCustomers(Serialization.deserializeCustomer());
        setCustomerArea(department.browseCustomerInformation());
    }

    @FXML
    protected void onSerializeOrderClick(ActionEvent actionEvent){
        Serialization.serializeOrder(department.getOrders());
    }

    @FXML
    protected void onOpenOrderClick(ActionEvent actionEvent) throws ClassNotFoundException {
        List<Order> orders = Serialization.deserializeOrder();
        department.clearOrders();
        department.clearCustomers();
        for (int i = 0; i< orders.size();i++){
            if(department.checkCustomer(orders.get(i).getCustomer())) {
                department.addCustomer(orders.get(i).getCustomer().getName(), orders.get(i).getCustomer().getPhoneNumber(), orders.get(i).getCustomer().getAddress());
            }
            department.addOrder(orders.get(i).getCustomer(),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
        }
        setCustomerArea(department.browseCustomerInformation());
        setOrderArea(department.browseOrderInformation());
    }

    @FXML
    protected void onAddOrderClick(ActionEvent actionEvent) throws ClassNotFoundException {
        List<Order> orders = Serialization.deserializeOrder();
        for (int i = 0; i< orders.size();i++){
            if(department.checkOrder(orders.get(i))){
                if(department.checkCustomer(orders.get(i).getCustomer())){
                    department.addCustomer(orders.get(i).getCustomer().getName(),orders.get(i).getCustomer().getPhoneNumber(),orders.get(i).getCustomer().getAddress());
                }
                department.addOrder(orders.get(i).getCustomer(),orders.get(i).getOrderDate(),orders.get(i).getOrderPrice());
            }
        }
        setCustomerArea(department.browseCustomerInformation());
        setOrderArea(department.browseOrderInformation());
    }

    @FXML
    protected void onAddCustomerClick(ActionEvent actionEvent) throws ClassNotFoundException {
        List<Customer> customers = Serialization.deserializeCustomer();
        for (int i = 0; i< customers.size();i++){
            if(department.checkCustomer(customers.get(i))){
                department.addCustomer(customers.get(i).getName(),customers.get(i).getPhoneNumber(),customers.get(i).getAddress());
            }
        }
        setCustomerArea(department.browseCustomerInformation());
    }

    public void setOrderArea(String str) {
        order.setText(str);
    }

    public void setCustomerArea(String str) {
        customer.setText(str);
    }
}