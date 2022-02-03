package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionListener extends Thread {

    private ServerSocket serverSocket;

    public ServerConnectionListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        int id = 1;
        try {
            while (true){
                Socket socket = null;
                socket = serverSocket.accept();
                Server newConnection = new Server(socket, id);
                System.out.println("User " + id + " connected");
                newConnection.start();
                ++id;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
