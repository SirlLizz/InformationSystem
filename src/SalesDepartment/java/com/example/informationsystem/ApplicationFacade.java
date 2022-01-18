package com.example.informationsystem;

import com.example.informationsystem.controller.*;
import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import com.example.informationsystem.reference.Find;
import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.reference.Serialization;
import com.example.informationsystem.view.AddCustomerView;
import com.example.informationsystem.view.ChangeCustomerView;
import com.example.informationsystem.view.MenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationFacade extends Application{

    //reference
    Find find = new Find();
    ReferenceSystem department = new ReferenceSystem();
    Serialization serialization = new Serialization();

    //controller
    MenuController menuController;
    AddOrderController addOrderController;
    AddCustomerController addCustomerController;
    ChangeOrderController changeOrderController;
    ChangeCustomerController changeCustomerController;

    //view
    MenuView menu;

    public void start(){
        launch(ApplicationFacade.class);
    }

    @Override
    public void start(Stage stage){
        addCustomerController = new AddCustomerController(department);
        addOrderController = new AddOrderController(department, addCustomerController);
        changeCustomerController = new ChangeCustomerController(department);
        changeOrderController = new ChangeOrderController(department, changeCustomerController);
        menuController = new MenuController(serialization, find, addOrderController, addCustomerController, changeOrderController, changeCustomerController);
        menu = new MenuView(department, menuController);
        menu.showStage();
    }
}
