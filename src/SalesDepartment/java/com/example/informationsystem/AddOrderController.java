package com.example.informationsystem;

import com.example.informationsystem.department.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
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
    protected void onAddOrderButtonClick(){
        ReferenceSystem.addOrder(customer, date.getValue(), Double.parseDouble(price.getText()));
        System.out.println(ReferenceSystem.browseOrderInformation());
        Stage stage = (Stage) addOrder.getScene().getWindow();
        stage.close();
    }

    public void OnShowingComboBox(Event event) {
        setComboBox();
    }

    private void setComboBox(){
        List<String> names = new ArrayList<>();
        names.add("New Customer");
        for (int i = 0; i < ReferenceSystem.getCustomers().size(); i++){
            names.add(ReferenceSystem.getCustomers().get(i).getName());
        }
        combo.setItems(FXCollections.observableArrayList(names));
    }

    public void onActionComboBox(ActionEvent actionEvent) throws IOException {
        System.out.println(combo.getValue());
        if(combo.getValue() == "New Customer"){
            AddCustomerController.createAddCustomer();
        }else{
            for (int i = 0; i < ReferenceSystem.getCustomers().size(); i++){
                if(ReferenceSystem.getCustomers().get(i).getName() == combo.getValue()){
                    customer = ReferenceSystem.getCustomers().get(i);
                }
            }
        }
    }
}
