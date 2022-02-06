import controller.ServerConnectionListener;

import java.net.ServerSocket;

public class Main {
    private static final int PORT = 4000;

    public static void main(String[] args) throws Exception {
        System.out.println("Server started!");
        ServerConnectionListener connectionListener = new ServerConnectionListener(new ServerSocket(PORT));
        connectionListener.start();

    }
}
