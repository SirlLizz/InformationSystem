package com.example.informationsystem;

import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.view.MenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    ReferenceSystem department = new ReferenceSystem();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MenuView menu = new MenuView(department);
        menu.showStage();
    }
}