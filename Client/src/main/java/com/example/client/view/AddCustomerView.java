package com.example.client.view;

import com.example.shared.reference.ReferenceSystem;
import com.example.shared.transport.Request;
import com.example.shared.transport.Response;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
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

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Request request;
    private Response response;

    public AddCustomerView(ObjectInputStream in, ObjectOutputStream out, Request request, Response response){
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
    private void onAddCustomerButtonClick() {
        request.setCommand("/add/customer");
        request.setArgs(new String[]{fullName.getText(), telephone.getText(), address.getText()});
        serverDataExchange();
        stage.close();
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
