package informatinsystem.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddOrderView {

    public AddOrderView(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("AddOrder.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Add Customer");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
