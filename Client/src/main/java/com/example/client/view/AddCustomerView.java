package com.example.client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import reference.ReferenceSystem;
import transport.Request;
import transport.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class AddCustomerView {

    private final Stage stage;

    @FXML
    private Button addCustomer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    private ReferenceSystem department;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;

    public AddCustomerView(ReferenceSystem department, ObjectInputStream in, ObjectOutputStream out, Request request, Response response){
        this.department = department;
        this.in = in;
        this.out = out;
        this.request = request;
        this.response = response;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add Customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showStage() {
        stage.showAndWait();
    }

    @FXML
    private void initialize() {
        addCustomer.setOnAction(event -> onAddCustomerButtonClick());
    }

    @FXML
    protected void onAddCustomerButtonClick() {
        request.setCommand("/add/customer");
        request.setArgs(new String[]{fullName.getText(), telephone.getText(), address.getText()});
        try {
            out.reset();
            out.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(request, out);
            out.writeObject(null);
            out.flush();
            jaxbMarshaller.marshal(request, System.out);
        }catch (JAXBException | IOException e){
            e.printStackTrace();
        }
         */
        try{
            response = (Response) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*
        try{
            JAXBContext jaxbInContext = JAXBContext.newInstance(Response.class);
            Unmarshaller jaxbInUnmarshaller = jaxbInContext.createUnmarshaller();
            response = ((Response) jaxbInUnmarshaller.unmarshal(in));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        */
        department.setOrders(response.getDepartment().getOrders());
        department.setCustomers(response.getDepartment().getCustomers());
        stage.close();
    }
}
