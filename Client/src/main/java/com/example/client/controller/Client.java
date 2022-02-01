package com.example.client.controller;

import com.example.client.reference.ReferenceSystem;
import com.example.client.transport.Request;
import com.example.client.transport.Response;
import com.example.client.view.MenuView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Application {
    private static int PORT = 4000;
    private static String HOST = "127.0.0.1";

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ReferenceSystem department = new ReferenceSystem();
    private Request reqest;
    private Response response;

    private MenuView menu;


    @Override
    public void start(Stage stage) throws IOException {
        socket = new Socket(HOST, PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        reqest = new Request();
        response = new Response();
        menu = new MenuView(department, in, out, reqest, response);
        menu.showStage();
    }
}