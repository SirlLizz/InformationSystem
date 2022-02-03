package com.example.client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import reference.ReferenceSystem;
import transport.Request;
import transport.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChangeCustomerView {

    private final Stage stage;

    @FXML
    private Button addCustomer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    private ReferenceSystem department;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;
    private int customerId;

    public ChangeCustomerView(ReferenceSystem department, ObjectInputStream in, ObjectOutputStream out, Request request, Response response, int customerID){
        this.department = department;
        this.in = in;
        this.out = out;
        this.request = request;
        this.response = response;
        this.customerId = customerID;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Change Customer");
            addCustomer.setText("Change");
            fullName.setText(department.getCustomerFromID(customerID).getName());
            telephone.setText(department.getCustomerFromID(customerID).getPhoneNumber());
            address.setText(department.getCustomerFromID(customerID).getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage.showAndWait();
    }

    @FXML
    private void initialize() {
        addCustomer.setOnAction(event -> onChangeCustomerButtonClick());
    }

    @FXML
    protected void onChangeCustomerButtonClick() {
        request.setCommand("/change/customer");
        request.setArgs(new String[]{String.valueOf(customerId), fullName.getText(), telephone.getText(), address.getText()});
        try{
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
    }

}
