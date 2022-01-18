package com.example.informationsystem;

import com.example.informationsystem.reference.ReferenceSystem;
import com.example.informationsystem.view.MenuView;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main{

    public static void main(String[] args){
        ApplicationFacade facade = new ApplicationFacade();
        facade.start();
    }

}