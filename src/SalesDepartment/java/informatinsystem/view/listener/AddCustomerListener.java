package informatinsystem.view.listener;

import informatinsystem.department.ReferenceSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerListener {

    @FXML
    private Button addCustomer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    private ReferenceSystem department = new ReferenceSystem();

    @FXML
    protected void onAddCustomerButtonClick() {
        department.addCustomer(fullName.getText(), telephone.getText(), address.getText());
        System.out.println(department.browseCustomerInformation());
        Stage stage = (Stage) addCustomer.getScene().getWindow();
        stage.close();
        //AddCustomerController.addCustomerButton(addCustomer, fullName, telephone, address);
    }
}
