package informatinsystem.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCustomerView {

    public AddCustomerView() {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Add Customer");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
