package com.example.informationsystem.controller;

import com.example.informationsystem.department.ReferenceSystem;
import com.example.informationsystem.department.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddOrderController {

    Customer customer = null;

    @FXML
    public Button addOrder;
    @FXML
    private ComboBox<String> combo;
    @FXML
    public DatePicker date;
    @FXML
    private TextField price;

    public static void createAddOrder() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AddOrderController.class.getResource("AddOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Order");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onAddOrderButtonClick(ActionEvent actionEvent){
        if(checkPriceToDouble(price.getText()) && checkCustomer()) {
            ReferenceSystem.addOrder(customer, date.getValue(), Double.parseDouble(price.getText()));
            System.out.println(ReferenceSystem.browseOrderInformation());
            Stage stage = (Stage) addOrder.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void onActionComboBox(ActionEvent actionEvent){
        for (int i = 0; i < ReferenceSystem.getCustomers().size(); i++) {
            if (ReferenceSystem.getCustomers().get(i).getName().equals(combo.getValue())) {
                customer = ReferenceSystem.getCustomers().get(i);
            }
        }
    }

    @FXML
    private void onAddCustomerClick(ActionEvent actionEvent) throws IOException {
        AddCustomerController.createAddCustomer();
    }

    public void OnShowingComboBox(Event event) {
        setComboBox();
    }

    private void setComboBox(){
        List<String> names = new ArrayList<>();
        for (int i = 0; i < ReferenceSystem.getCustomers().size(); i++){
            names.add(ReferenceSystem.getCustomers().get(i).getName());
        }
        combo.setItems(FXCollections.observableArrayList(names));
    }

    private boolean checkPriceToDouble(String price) {
        try {
            Double priceDouble = Double.parseDouble(price);
            return true;
        } catch(NumberFormatException e){
            alertPrice();
        }
        return false;
    }

    private void alertPrice() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Price Alert");
        alert.setHeaderText("Price is incorrect!");
        alert.showAndWait();
    }

    private boolean checkCustomer() {
        if(customer == null){
            alertCustomer();
            return false;
        }
        return true;
    }

    private void alertCustomer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Alert");
        alert.setHeaderText("Customer is null!");
        alert.showAndWait();
    }
}
