package com.example.client.view;

import com.example.client.model.Customer;
import com.example.client.model.Order;
import com.example.client.reference.ReferenceSystem;
import com.example.client.transport.Request;
import com.example.client.transport.Response;
import com.example.client.transport.Response;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

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
    private MenuItem sortOrderToHigh;
    @FXML
    private MenuItem sortOrderToLow;
    @FXML
    private MenuItem sortCustomerToHigh;
    @FXML
    private MenuItem sortCustomerToLow;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button changeCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button addOrderButton;
    @FXML
    private Button changeOrderButton;
    @FXML
    private Button deleteOrderButton;

    private ReferenceSystem department;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;

    public MenuView(ReferenceSystem department, ObjectInputStream in, ObjectOutputStream out, Request request, Response response) {
        this.department = department;
        this.in = in;
        this.out = out;
        this.request = request;
        this.response = response;
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
        addCustomerButton.setOnAction(event -> onAddCustomerClick());
        /*
        serializeOrderButton.setOnAction(event -> onSerializeOrderClick());
        openOrderButton.setOnAction(event -> onOpenOrderClick());
        addOpenOrderButton.setOnAction(event -> onAddOpenOrderClick());
        serializeCustomerButton.setOnAction(event -> onSerializeCustomerClick());
        openCustomerButton.setOnAction(event -> onOpenCustomersClick());
        addOpenCustomerButton.setOnAction(event -> onAddOpenCustomersClick());
        findDateButton.setOnAction(event -> onFindDateClick());
        findPriceButton.setOnAction(event -> onFindPriceClick());
        findFullNameButton.setOnAction(event -> onFindFullNameClick());
        findNumberButton.setOnAction(event -> onFindNumberClick());
        findAddressButton.setOnAction(event -> onFindAddressClick());
        sortCustomerToHigh.setOnAction(event -> onSortCustomerToHigh());
        sortCustomerToLow.setOnAction(event -> onSortCustomerToLow());
        sortOrderToHigh.setOnAction(event -> onSortOrderToHigh());
        sortOrderToLow.setOnAction(event -> onSortOrderToLow());
        changeCustomerButton.setOnAction(event -> onChangeCustomerClick());
        deleteCustomerButton.setOnAction(event -> onDeleteCustomerClick());
        addOrderButton.setOnAction(event -> onAddOrderClick());
        changeOrderButton.setOnAction(event -> onChangeOrderClick());
        deleteOrderButton.setOnAction(event -> onDeleteOrderClick());
         */
    }

    private void onAddCustomerClick(){
        try{
            AddCustomerView addCust = new AddCustomerView(department, out, request);
            addCust.showStage();
            //responce = (Response) in.readObject();
            JAXBContext jaxbInContext = JAXBContext.newInstance(Response.class);
            Unmarshaller jaxbInUnmarshaller = jaxbInContext.createUnmarshaller();
            tableCustomer.setItems(FXCollections.observableArrayList(((Response) jaxbInUnmarshaller.unmarshal(in)).getDepartment().getCustomers()));
        }catch (JAXBException e){
            e.printStackTrace();
        }

    }

    /*
    private void onDeleteCustomerClick() {
        TableView.TableViewSelectionModel<Customer> selectionModel = tableCustomer.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            controller.deleteCustomerClick(selectionModel.getSelectedItem().getCustomerID());
            tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
            tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        }
    }

    private void onChangeCustomerClick() {
        TableView.TableViewSelectionModel<Customer> selectionModel = tableCustomer.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            controller.changeCustomerClick(selectionModel.getSelectedItem().getCustomerID());
            tableCustomer.refresh();
            tableOrder.refresh();
        }
    }

    private void onAddOrderClick(){
        controller.addOrderClick();
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onDeleteOrderClick() {
        TableView.TableViewSelectionModel<Order> selectionModel = tableOrder.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            controller.deleteOrderClick(selectionModel.getSelectedItem().getOrderID());
            tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        }
    }

    private void onChangeOrderClick() {
        TableView.TableViewSelectionModel<Order> selectionModel = tableOrder.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            controller.changeOrderClick(selectionModel.getSelectedItem().getOrderID());
            tableCustomer.refresh();
            tableOrder.refresh();
        }
    }

    private void onSerializeOrderClick(){
        File file = saveDialog("Serialized Order", "*.xml");
        controller.serializeOrder(file);
    }

    private void onOpenOrderClick() {
        File file = openDialog("Serialized Order", "*.xml");
        controller.openOrder(file);
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onAddOpenOrderClick() {
        File file = openDialog("Serialized Order", "*.xml");
        controller.addOpenOrder(file);
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onSerializeCustomerClick(){
        File file = saveDialog("Serialized Customer", "*.xml");
        controller.serializeCustomer(file);
    }

    private void onOpenCustomersClick() {
        File file = openDialog("Serialized Customer", "*.xml");
        controller.openCustomer(file);
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onAddOpenCustomersClick() {
        File file = openDialog("Serialized Customer", "*.xml");
        controller.addOpenCustomer(file);
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onFindDateClick(){
        String pattern = inputDialog("Date");
        outOrderDialog("Date", controller.findDate(pattern));
    }

    private void onFindPriceClick() {
        String pattern = inputDialog("Price");
        outOrderDialog("Price", controller.findPrice(pattern));
    }

    private void onFindFullNameClick() {
        String pattern = inputDialog("Full Name");
        outOrderDialog("Full Name", controller.findFullName(pattern));
    }

    private void onFindNumberClick() {
        String pattern = inputDialog("Number");
        outOrderDialog("Number", controller.findNumber(pattern));
    }

    private void onFindAddressClick() {
        String pattern = inputDialog("Address");
        outOrderDialog("Address", controller.findAddress(pattern));
    }

    private void onSortCustomerToHigh() {
        controller.sortCustomerToHigh();
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onSortCustomerToLow() {
        controller.sortCustomerToLow();
        tableCustomer.setItems(FXCollections.observableArrayList(department.getCustomers()));
    }

    private void onSortOrderToHigh() {
        controller.sortOrderToHigh();
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
    }

    private void onSortOrderToLow() {
        controller.sortOrderToLow();
        tableOrder.setItems(FXCollections.observableArrayList(department.getOrders()));
    }

    private String inputDialog(String name) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find date on using pattern");
        dialog.setContentText("Please enter " + name + ":");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
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

    private void outOrderDialog(String head, List<Order> orders) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FIND");
        alert.setHeaderText("Find in " + head + ":");
        StringBuilder out = new StringBuilder();
        for(int i = 0; i<orders.size();i++){
            out.append(orders.get(i).toString()).append("\n");
        }
        VBox dialogPaneContent = new VBox();
        TextArea textArea = new TextArea();
        textArea.setText(out.toString());
        dialogPaneContent.getChildren().addAll(textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.setContentText(out.toString());
        alert.showAndWait();
    }
     */

}
