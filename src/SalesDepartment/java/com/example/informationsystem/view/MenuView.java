package com.example.informationsystem.view;

import com.example.informationsystem.controller.MenuController;
import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import com.example.informationsystem.reference.ReferenceSystem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class MenuView {

    private final Stage stage;

    @FXML
    private TableView<Order> tableOrder;
    @FXML
    private TableColumn<Order, Integer> columnOrderID;
    @FXML
    private TableColumn<Order, Customer> columnCustomer;
    @FXML
    private TableColumn<Order, LocalDate> columnDate;
    @FXML
    private TableColumn<Order, Double> columnPrice;
    @FXML
    private TableView<Customer> tableCustomer;
    @FXML
    private TableColumn<Customer, Integer> columnCustomerID;
    @FXML
    private TableColumn<Customer, String> columnName;
    @FXML
    private TableColumn<Customer, String> columnPhone;
    @FXML
    private TableColumn<Customer, String> columnAddress;
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
    private MenuItem findDateButton;
    @FXML
    private MenuItem findPriceButton;
    @FXML
    private MenuItem findFullNameButton;
    @FXML
    private MenuItem findNumberButton;
    @FXML
    private MenuItem findAddressButton;
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
        columnOrderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
        columnCustomer.setCellValueFactory(new PropertyValueFactory<Order, Customer>("customer"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Order, Double>("orderPrice"));
        columnCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
        columnName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        addCustomerButton.setOnAction(event -> onCustomerClick());
        addOrderButton.setOnAction(event -> onOrderClick());
        serializeOrderButton.setOnAction(event -> onSerializeOrderClick());
        openOrderButton.setOnAction(event -> onOpenOrderClick());
        addOpenOrderButton.setOnAction(event -> onAddOrderClick());
        serializeCustomerButton.setOnAction(event -> onSerializeCustomerClick());
        openCustomerButton.setOnAction(event -> onOpenCustomerClick());
        addOpenCustomerButton.setOnAction(event -> onAddCustomerClick());
        findDateButton.setOnAction(event -> onFindDateClick());
        findPriceButton.setOnAction(event -> onFindPriceClick());
        findFullNameButton.setOnAction(event -> onFindFullNameClick());
        findNumberButton.setOnAction(event -> onFindNumberClick());
        findAddressButton.setOnAction(event -> onFindAddressClick());
    }

    private void onCustomerClick() {
        controller.addCustomerClick(department);
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onOrderClick(){
        controller.addOrderClick(department);
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onSerializeOrderClick(){
        File file = saveDialog("Serialized Order", "*.ord");
        controller.serializeOrder(department, file);
    }

    private void onOpenOrderClick() {
        File file = openDialog("Serialized Order", "*.ord");
        controller.openOrder(department, file);
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onAddOrderClick() {
        File file = openDialog("Serialized Order", "*.ord");
        controller.addOpenOrder(department, file);
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onSerializeCustomerClick(){
        File file = saveDialog("Serialized Customer", "*.cust");
        controller.serializeCustomer(department, file);
    }

    private void onOpenCustomerClick() {
        File file = openDialog("Serialized Customer", "*.cust");
        controller.openCustomer(department, file);
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onAddCustomerClick() {
        File file = openDialog("Serialized Customer", "*.cust");
        controller.addOpenCustomer(department, file);
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onFindDateClick(){
        String pattern = inputDialog("Date");
        System.out.println(controller.findDate(department, pattern).toString());
    }

    private void onFindPriceClick() {
        String pattern = inputDialog("Price");
        System.out.println(controller.findPrice(department, pattern).toString());
    }

    private void onFindFullNameClick() {
        String pattern = inputDialog("Full Name");
        System.out.println(controller.findFullName(department, pattern).toString());
    }

    private void onFindNumberClick() {
        String pattern = inputDialog("Number");
        System.out.println(controller.findNumber(department, pattern).toString());
    }

    private void onFindAddressClick() {
        String pattern = inputDialog("Address");
        System.out.println(controller.findAddress(department, pattern).toString());
    }

    private String inputDialog(String name) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find date on using pattern");
        dialog.setContentText("Please enter " + name + ":");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }

    private File saveDialog(String filterName, String filterType) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(filterName, filterType));
        Stage stage = new Stage();
        return chooser.showSaveDialog(stage);
    }

    private File openDialog(String filterName, String filterType) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(filterName, filterType));
        Stage stage = new Stage();
        return chooser.showOpenDialog(stage);
    }

}
