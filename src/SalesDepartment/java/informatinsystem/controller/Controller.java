package informatinsystem.controller;

import informatinsystem.department.ReferenceSystem;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    private ReferenceSystem department = new ReferenceSystem();

    public void addCustomerButton(Button addCustomer, TextField fullName, TextField telephone, TextField address){
        department.addCustomer(fullName.getText(), telephone.getText(), address.getText());
        System.out.println(department.browseCustomerInformation());
        Stage stage = (Stage) addCustomer.getScene().getWindow();
        stage.close();
    }
}
