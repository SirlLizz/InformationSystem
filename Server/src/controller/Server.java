package controller;

import com.example.client.transport.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;
import controller.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

class Server extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int id;

    public Server(Socket socket, int id) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client run " + id);
            while (!socket.isClosed()){
                try {
                    //Request request = (Request) in.readObject();

                    JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    Request request = (Request)jaxbUnmarshaller.unmarshal(in);

                    System.out.println(request.getCommand());
                    System.out.println(Arrays.toString(request.getArgs()));

                } catch (JAXBException e) {
                    System.out.println("Failed connection with " + id);
                    e.printStackTrace();
                    break;
                }
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Output data failed");
            e.printStackTrace();
        }
    }
}
