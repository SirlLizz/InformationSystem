package com.example.informationsystem.view;

import com.example.informationsystem.controller.AddOrderController;
import com.example.informationsystem.controller.ChangeOrderController;
import com.example.informationsystem.model.Customer;
import com.example.informationsystem.reference.ReferenceSystem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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

    Customer customer = null;
    ReferenceSystem department = null;
    int orderID = 0;
    ChangeOrderController controller = new ChangeOrderController();

    public ChangeOrderView(ReferenceSystem department, int orderID){
        this.orderID = orderID;
        this.department = department;
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
        if(controller.checkPriceToDouble(price.getText())){
            if(controller.checkCustomer(customer)){
                controller.changeOrderClick(department, orderID, customer, date.getValue(), Double.parseDouble(price.getText()));
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

    private void onChangeCustomerClick(){
        controller.changeCustomerClick(department, customer.getCustomerID());
    }

    private void onActionComboBox(){
        customer = controller.actionComboBox(department, combo.getValue());
    }

    private void setComboBox(){
        combo.setItems(FXCollections.observableArrayList(controller.showComboBox(department)));
    }

}
