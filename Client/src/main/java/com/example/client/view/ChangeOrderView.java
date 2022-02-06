package com.example.client.view;


import com.example.shared.model.Customer;
import com.example.shared.reference.ReferenceSystem;
import com.example.shared.transport.Request;
import com.example.shared.transport.Response;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;
    private Customer customer = null;
    String orderID;

    public ChangeOrderView(ObjectInputStream in, ObjectOutputStream out, Request request, Response response, String orderID){
        this.in = in;
        this.out = out;
        this.request = request;
        this.response = response;
        this.orderID = orderID;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOrder.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Change Order");
            addOrder.setText("Change");
            addCustomer.setText("Change");
            request.setCommand("/get/reference");
            serverDataExchange();
            price.setText(Double.toString(response.getDepartment().getOrderFromID(orderID).getOrderPrice()));
            date.setValue(response.getDepartment().getOrderFromID(orderID).getOrderDate());
            customer = response.getDepartment().getOrderFromID(orderID).getCustomer();
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
        if(checkPriceToDouble(price.getText())){
            if(checkCustomer(customer)){
                request.setCommand("/change/order");
                request.setArgs(new String[]{String.valueOf(orderID), customer.getName(), customer.getPhoneNumber(), customer.getAddress(), String.valueOf(date.getValue()), price.getText()});
                serverDataExchange();
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Alert");
                alert.setHeaderText("Customer is incorrect!");
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
        ChangeCustomerView changeCust = new ChangeCustomerView(in, out, request, response, customer.getCustomerID());
        changeCust.showStage();
    }

    private void onActionComboBox(){
        request.setCommand("/get/reference");
        serverDataExchange();
        for (int i = 0; i < response.getDepartment().getCustomers().size(); i++) {
            if (response.getDepartment().getCustomers().get(i).getName().equals(combo.getValue())) {
                customer = response.getDepartment().getCustomers().get(i);
            }
        }
    }

    private void setComboBox(){
        List<String> names = new ArrayList<>();
        request.setCommand("/get/reference");
        serverDataExchange();
        for (int i = 0; i < response.getDepartment().getCustomers().size(); i++){
            names.add(response.getDepartment().getCustomers().get(i).getName());
        }
        combo.setItems(FXCollections.observableArrayList(names));
    }

    private boolean checkPriceToDouble(String price) {
        try {
            Double priceDouble = Double.parseDouble(price);
            return true;
        } catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkCustomer(Customer customer) {
        request.setCommand("/get/reference");
        serverDataExchange();
        if (customer != null && response.getDepartment().checkCustomer(customer)!=-1){
            return true;
        }
        return false;
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

}
