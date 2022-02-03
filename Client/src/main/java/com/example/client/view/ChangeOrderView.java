package com.example.client.view;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;
import reference.ReferenceSystem;
import transport.Request;
import transport.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ChangeOrderView {

    private final Stage stage;

    @FXML
    public Button addOrder;
    @FXML
    private Button addCustomer;
    @FXML
    private ComboBox<String> combo;
    @FXML
    public DatePicker date;
    @FXML
    private TextField price;

    private ReferenceSystem department;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;
    private Customer customer = null;
    int orderID;

    public ChangeOrderView(ReferenceSystem department, ObjectInputStream in, ObjectOutputStream out, Request request, Response response, int orderID){
        this.department = department;
        this.in = in;
        this.out = out;
        this.request = request;
        this.response = response;
        this.orderID = orderID;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOrder.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Change Order");
            addOrder.setText("Change");
            addCustomer.setText("Change");
            price.setText(Double.toString(department.getOrderFromID(orderID).getOrderPrice()));
            date.setValue(department.getOrderFromID(orderID).getOrderDate());
            customer = department.getOrderFromID(orderID).getCustomer();
            combo.setPromptText(customer.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage.showAndWait();
    }

    @FXML
    private void initialize() {
        addOrder.setOnAction(event -> onChangeOrderButtonClick());
        addCustomer.setOnAction(event -> onChangeCustomerClick());
        combo.setOnAction(event -> onActionComboBox());
        combo.setOnShowing(event -> setComboBox());
    }

    private void onChangeOrderButtonClick(){
        if(checkPriceToDouble(price.getText())){
            if(checkCustomer(customer)){
                try {
                    request.setCommand("/change/order");
                    request.setArgs(new String[]{String.valueOf(orderID), customer.getName(), customer.getPhoneNumber(), customer.getAddress(), String.valueOf(date.getValue()), price.getText()});
                    out.reset();
                    out.writeObject(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    response = (Response) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                department.setOrders(response.getDepartment().getOrders());
                department.setCustomers(response.getDepartment().getCustomers());
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Alert");
                alert.setHeaderText("Customer is incorrect!");
                alert.showAndWait();
                alert.close();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Price Alert");
            alert.setHeaderText("Price is incorrect!");
            alert.showAndWait();
            alert.close();
        }
    }

    private void onChangeCustomerClick(){
        ChangeCustomerView changeCust = new ChangeCustomerView(department, in, out, request, response, customer.getCustomerID());
        changeCust.showStage();
    }

    private void onActionComboBox(){
        for (int i = 0; i < department.getCustomers().size(); i++) {
            if (department.getCustomers().get(i).getName().equals(combo.getValue())) {
                customer = department.getCustomers().get(i);
            }
        }
    }

    private void setComboBox(){
        List<String> names = new ArrayList<>();
        for (int i = 0; i < department.getCustomers().size(); i++){
            names.add(department.getCustomers().get(i).getName());
        }
        combo.setItems(FXCollections.observableArrayList(names));
    }

    private boolean checkPriceToDouble(String price) {
        try {
            Double priceDouble = Double.parseDouble(price);
            return true;
        } catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkCustomer(Customer customer) {
        if (customer != null && department.checkCustomer(customer)!=-1){
            return true;
        }
        return false;
    }

}
