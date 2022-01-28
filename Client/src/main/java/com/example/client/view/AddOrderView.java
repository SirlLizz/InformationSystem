package com.example.client.view;

import com.example.client.model.Customer;
import com.example.client.reference.ReferenceSystem;
import com.example.client.model.Customer;
import com.example.client.reference.ReferenceSystem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddOrderView {

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

    Customer customer = null;
    ReferenceSystem department;

    public AddOrderView(ReferenceSystem department){
        this.department = department;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOrder.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add Order");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showStage() {
        stage.showAndWait();
    }

    /*
    @FXML
    private void initialize() {
        addOrder.setOnAction(event -> onAddOrderButtonClick());
        addCustomer.setOnAction(event -> onAddCustomerClick());
        combo.setOnAction(event -> onActionComboBox());
        combo.setOnShowing(event -> setComboBox());
    }

    private void onAddOrderButtonClick(){
        if(controller.checkPriceToDouble(price.getText())){
            if(controller.checkCustomer(customer)){
                controller.addOrderClick(customer, date.getValue(), Double.parseDouble(price.getText()));
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Alert");
                alert.setHeaderText("Customer is null!");
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

    private void onAddCustomerClick(){
        controller.addCustomerClick();
    }

    private void onActionComboBox(){
        customer = controller.actionComboBox(combo.getValue());
    }

    private void setComboBox(){
        combo.setItems(FXCollections.observableArrayList(controller.showComboBox()));
    }

     */

}