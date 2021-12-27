package com.example.informationsystem.view;

import com.example.informationsystem.controller.MenuController;
import com.example.informationsystem.reference.ReferenceSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuView {

    private final Stage stage;

    @FXML
    private TextArea customer;
    @FXML
    private TextArea order;
    @FXML
    private MenuItem serializeOrderButton;
    @FXML
    private MenuItem openOrderButton;
    @FXML
    private MenuItem addOpenOrderButton;
    @FXML
    private MenuItem serializeCustomerButton;
    @FXML
    private MenuItem openCustomerButton;
    @FXML
    private MenuItem addOpenCustomerButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button addOrderButton;

    ReferenceSystem department = null;
    MenuController controller = new MenuController();

    public MenuView(ReferenceSystem department) {
        this.department = department;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Department manager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage.showAndWait();
    }

    @FXML
    private void initialize() {
        addCustomerButton.setOnAction(event -> onCustomerClick());
        addOrderButton.setOnAction(event -> onOrderClick());
        serializeOrderButton.setOnAction(event -> onSerializeOrderClick());
        openOrderButton.setOnAction(event -> onOpenOrderClick());
        addOpenOrderButton.setOnAction(event -> onAddOrderClick());
        serializeCustomerButton.setOnAction(event -> onSerializeCustomerClick());
        openCustomerButton.setOnAction(event -> onOpenCustomerClick());
        addOpenCustomerButton.setOnAction(event -> onAddCustomerClick());
    }

    private void onCustomerClick() {
        controller.addCustomerClick(department);
    }

    private void onOrderClick(){
        controller.addOrderClick(department);
    }

    private void onSerializeOrderClick(){
        controller.serializeOrder(department);
    }

    private void onOpenOrderClick() {
        controller.openOrder(department);
    }

    private void onAddOrderClick() {
        controller.addOpenOrder(department);
    }

    private void onSerializeCustomerClick(){
        controller.serializeCustomer(department);
    }

    private void onOpenCustomerClick() {
        controller.openCustomer(department);
    }

    private void onAddCustomerClick() {
        controller.addOpenCustomer(department);
    }

    public void setOrderArea(String str) {
        order.setText(str);
    }

    public void setCustomerArea(String str) {
        customer.setText(str);
    }

}
