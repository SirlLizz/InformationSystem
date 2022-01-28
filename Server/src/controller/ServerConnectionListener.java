package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionListener {

    private ServerSocket serverSocket;

    public ServerConnectionListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() throws Exception {
        int id = 1;
        while (true){
            Socket socket = serverSocket.accept();
            Server newConnection = new Server(socket, id);
            System.out.println("User " + id + " connected");
            newConnection.start();
            ++id;
        }
    }

}
