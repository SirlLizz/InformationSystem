package informatinsystem.view.listener;

import informatinsystem.department.ReferenceSystem;
import informatinsystem.department.Customer;
import informatinsystem.view.AddCustomerView;
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

public class AddOrderListener {

    Customer customer = null;

    private ReferenceSystem department = new ReferenceSystem();

    @FXML
    public Button addOrder;
    @FXML
    private ComboBox<String> combo;
    @FXML
    public DatePicker date;
    @FXML
    private TextField price;

    @FXML
    private void onAddOrderButtonClick(ActionEvent actionEvent){
        if(checkPriceToDouble(price.getText()) && checkCustomer()) {
            department.addOrder(customer, date.getValue(), Double.parseDouble(price.getText()));
            System.out.println(department.browseOrderInformation());
            Stage stage = (Stage) addOrder.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void onActionComboBox(ActionEvent actionEvent){
        for (int i = 0; i < department.getCustomers().size(); i++) {
            if (department.getCustomers().get(i).getName().equals(combo.getValue())) {
                customer = department.getCustomers().get(i);
            }
        }
    }

    @FXML
    private void onAddCustomerClick(ActionEvent actionEvent) throws Exception {
        new AddCustomerView();
    }

    public void OnShowingComboBox(Event event) {
        setComboBox();
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
