package com.example.client.view;

import com.example.client.reference.Serialization;
import com.example.shared.model.Customer;
import com.example.shared.model.Order;
import com.example.shared.transport.Request;
import com.example.shared.transport.Response;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
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

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;
    private Serialization serialization = new Serialization();

    public MenuView(ObjectInputStream in, ObjectOutputStream out, Request request, Response response) {
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
        addOrderButton.setOnAction(event -> onAddOrderClick());

        serializeOrderButton.setOnAction(event -> onSerializeOrderClick());
        openOrderButton.setOnAction(event -> onOpenOrderClick());
        addOpenOrderButton.setOnAction(event -> onAddOpenOrderClick());
        serializeCustomerButton.setOnAction(event -> onSerializeCustomerClick());
        openCustomerButton.setOnAction(event -> onOpenCustomersClick());
        addOpenCustomerButton.setOnAction(event -> onAddOpenCustomersClick());

        changeCustomerButton.setOnAction(event -> onChangeCustomerClick());
        deleteCustomerButton.setOnAction(event -> onDeleteCustomerClick());
        changeOrderButton.setOnAction(event -> onChangeOrderClick());
        deleteOrderButton.setOnAction(event -> onDeleteOrderClick());

        findDateButton.setOnAction(event -> onFindDateClick());
        findPriceButton.setOnAction(event -> onFindPriceClick());
        findFullNameButton.setOnAction(event -> onFindFullNameClick());
        findNumberButton.setOnAction(event -> onFindNumberClick());
        findAddressButton.setOnAction(event -> onFindAddressClick());
        sortCustomerToHigh.setOnAction(event -> onSortCustomerToHigh());
        sortCustomerToLow.setOnAction(event -> onSortCustomerToLow());
        sortOrderToHigh.setOnAction(event -> onSortOrderToHigh());
        sortOrderToLow.setOnAction(event -> onSortOrderToLow());
    }

    private void onAddCustomerClick(){
        AddCustomerView addCust = new AddCustomerView(in, out, request, response);
        addCust.showStage();
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onAddOrderClick(){
        AddOrderView addOrd = new AddOrderView(in, out, request, response);
        addOrd.showStage();
        tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onDeleteCustomerClick() {
        TableView.TableViewSelectionModel<Customer> selectionModel = tableCustomer.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            request.setCommand("/delete/customer");
            request.setArgs(new String[]{String.valueOf(selectionModel.getSelectedItem().getCustomerID())});
            serverDataExchange();
            tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
            tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        }
    }

    private void onChangeCustomerClick() {
        TableView.TableViewSelectionModel<Customer> selectionModel = tableCustomer.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            ChangeCustomerView changeCust = new ChangeCustomerView(in, out, request, response, selectionModel.getSelectedItem().getCustomerID());
            changeCust.showStage();
            tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
            tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        }
    }

    private void onDeleteOrderClick() {
        TableView.TableViewSelectionModel<Order> selectionModel = tableOrder.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            request.setCommand("/delete/order");
            request.setArgs(new String[]{String.valueOf(selectionModel.getSelectedItem().getOrderID())});
            serverDataExchange();
            tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        }
    }

    private void onChangeOrderClick() {
        TableView.TableViewSelectionModel<Order> selectionModel = tableOrder.getSelectionModel();
        if(selectionModel.getSelectedItem()!=null){
            ChangeOrderView changeOrd = new ChangeOrderView(in, out, request, response, selectionModel.getSelectedItem().getOrderID());
            changeOrd.showStage();
            tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
            tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        }
    }

    private void onSerializeOrderClick(){
        File file = saveDialog("Serialized Order", "*.xml");
        request.setCommand("/get/reference");
        request.setArgs(new String[]{});
        serverDataExchange();
        serialization.serializeOrder(response.getDepartment().getOrders(), file);
    }

    private void onOpenOrderClick() {
        File file = openDialog("Serialized Order", "*.xml");
        request.setCommand("/open/order");
        List<Order> orders = serialization.deserializeOrder(file);
        String[][] requestMatrixArgs = new String[orders.size()][];
        for (int i = 0; i< orders.size();i++){
            requestMatrixArgs[i] = new String[]{orders.get(i).getCustomer().getName(), orders.get(i).getCustomer().getPhoneNumber(), orders.get(i).getCustomer().getAddress(), String.valueOf(orders.get(i).getOrderDate()), String.valueOf(orders.get(i).getOrderPrice())};
        }
        request.setMatrixArgs(requestMatrixArgs);
        serverDataExchange();
        tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onAddOpenOrderClick() {
        File file = openDialog("Serialized Order", "*.xml");
        request.setCommand("/open/add/order");
        List<Order> orders = serialization.deserializeOrder(file);
        String[][] requestMatrixArgs = new String[orders.size()][];
        for (int i = 0; i< orders.size();i++){
            requestMatrixArgs[i] = new String[]{orders.get(i).getCustomer().getName(), orders.get(i).getCustomer().getPhoneNumber(), orders.get(i).getCustomer().getAddress(), String.valueOf(orders.get(i).getOrderDate()), String.valueOf(orders.get(i).getOrderPrice())};
        }
        request.setMatrixArgs(requestMatrixArgs);
        serverDataExchange();
        tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onSerializeCustomerClick(){
        File file = saveDialog("Serialized Customer", "*.xml");
        request.setCommand("/get/reference");
        request.setArgs(new String[]{});
        serverDataExchange();
        serialization.serializeCustomer(response.getDepartment().getCustomers(), file);
    }

    private void onOpenCustomersClick() {
        File file = openDialog("Serialized Customer", "*.xml");
        request.setCommand("/open/customer");
        List<Customer> customers = serialization.deserializeCustomer(file);
        String[][] requestMatrixArgs = new String[customers.size()][];
        for (int i = 0; i< customers.size();i++){
            requestMatrixArgs[i] = new String[]{customers.get(i).getName(), customers.get(i).getPhoneNumber(), customers.get(i).getAddress()};
        }
        request.setMatrixArgs(requestMatrixArgs);
        serverDataExchange();
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onAddOpenCustomersClick() {
        File file = openDialog("Serialized Customer", "*.xml");
        request.setCommand("/open/add/customer");
        List<Customer> customers = serialization.deserializeCustomer(file);
        String[][] requestMatrixArgs = new String[customers.size()][];
        for (int i = 0; i< customers.size();i++){
            requestMatrixArgs[i] = new String[]{customers.get(i).getName(), customers.get(i).getPhoneNumber(), customers.get(i).getAddress()};
        }
        request.setMatrixArgs(requestMatrixArgs);
        serverDataExchange();
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onFindDateClick(){
        String pattern = inputDialog("Date");
        request.setCommand("/find/date");
        request.setArgs(new String[]{pattern});
        serverDataExchange();
        outOrderDialog("Date", response.getDepartment().getOrders());
    }

    private void onFindPriceClick() {
        String pattern = inputDialog("Price");
        request.setCommand("/find/price");
        request.setArgs(new String[]{pattern});
        serverDataExchange();
        outOrderDialog("Price", response.getDepartment().getOrders());
    }

    private void onFindFullNameClick() {
        String pattern = inputDialog("Full Name");
        request.setCommand("/find/fullname");
        request.setArgs(new String[]{pattern});
        serverDataExchange();
        outOrderDialog("Full Name", response.getDepartment().getOrders());
    }

    private void onFindNumberClick() {
        String pattern = inputDialog("Number");
        request.setCommand("/find/number");
        request.setArgs(new String[]{pattern});
        serverDataExchange();
        outOrderDialog("Number", response.getDepartment().getOrders());
    }

    private void onFindAddressClick() {
        String pattern = inputDialog("Address");
        request.setCommand("/find/address");
        request.setArgs(new String[]{pattern});
        serverDataExchange();
        outOrderDialog("Address", response.getDepartment().getOrders());

    }

    private void onSortCustomerToHigh() {
        request.setCommand("/sort/customer/high");
        serverDataExchange();
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onSortCustomerToLow() {
        request.setCommand("/sort/customer/low");
        serverDataExchange();
        tableCustomer.setItems(FXCollections.observableArrayList(response.getDepartment().getCustomers()));
    }

    private void onSortOrderToHigh() {
        request.setCommand("/sort/order/high");
        serverDataExchange();
        tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
    }

    private void onSortOrderToLow() {
        request.setCommand("/sort/order/low");
        serverDataExchange();
        tableOrder.setItems(FXCollections.observableArrayList(response.getDepartment().getOrders()));
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

    private void serverDataExchange(){
        try{
            ByteArrayOutputStream requestInMemory = new ByteArrayOutputStream();
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(request, requestInMemory);

            String respString = requestInMemory.toString();
            out.writeObject(respString);
            out.flush();
        }catch (JAXBException | IOException e){
            e.printStackTrace();
        }
        try{
            String rawResponse = (String)in.readObject();
            JAXBContext jaxbInContext = JAXBContext.newInstance(Response.class);
            Unmarshaller jaxbInUnmarshaller = jaxbInContext.createUnmarshaller();
            response.setDepartment(((Response)jaxbInUnmarshaller.unmarshal(new ByteArrayInputStream(rawResponse.getBytes()))).getDepartment());
        } catch (JAXBException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void outOrderDialog(String head, List<Order> orders) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FIND");
        alert.setHeaderText("Find in " + head + ":");
        StringBuilder out = new StringBuilder();
        for (Order order : orders) {
            out.append(order.toString()).append("\n");
        }
        VBox dialogPaneContent = new VBox();
        TextArea textArea = new TextArea();
        textArea.setText(out.toString());
        dialogPaneContent.getChildren().addAll(textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.setContentText(out.toString());
        alert.showAndWait();
    }


}
