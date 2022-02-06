package controller;

import com.example.shared.reference.ReferenceSystem;
import com.example.shared.transport.Request;
import com.example.shared.transport.Response;

import java.io.*;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

class Server extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int id;
    private RequestHandler requestHandler;

    public Server(Socket socket, int id) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        requestHandler = new RequestHandler();
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client run " + id);
            while (!socket.isClosed()){
                try {
                    String rawRequest = (String)in.readObject();
                    JAXBContext jaxbInContext = JAXBContext.newInstance(Request.class);
                    Unmarshaller jaxbInUnmarshaller = jaxbInContext.createUnmarshaller();
                    Request request = (Request)jaxbInUnmarshaller.unmarshal(new ByteArrayInputStream(rawRequest.getBytes()));

                    System.out.println("Client "+ id + " command: " + request.getCommand());

                    requestHandler.setRequest(request);
                    Response response = requestHandler.performRequest();

                    ByteArrayOutputStream responseInMemory = new ByteArrayOutputStream();
                    JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    jaxbMarshaller.marshal(response, responseInMemory);
                    out.writeObject(responseInMemory.toString());
                    out.flush();
                } catch (IOException e) {
                    System.out.println("Failed connection with " + id);
                    break;
                }
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Output data failed");
        }
    }
}
