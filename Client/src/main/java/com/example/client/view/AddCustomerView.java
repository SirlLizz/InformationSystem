package com.example.client.view;

import com.example.client.reference.ReferenceSystem;
import com.example.client.transport.Request;
import com.example.client.transport.Responce;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    ReferenceSystem department;
    ObjectOutputStream out;
    Request request;

    public AddCustomerView(ReferenceSystem department, ObjectOutputStream out, Request request){
        this.department = department;
        this.out = out;
        this.request = request;
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
            //out.writeObject(request);

            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(request, out);
            out.writeObject(null);
            out.flush();
            jaxbMarshaller.marshal(request, System.out);

            System.out.println("OK");
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();
    }

}
