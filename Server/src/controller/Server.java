package controller;

import transport.Request;
import transport.Response;
import reference.ReferenceSystem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

class Server extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int id;
    private RequestHandler requestHandler;
    private ReferenceSystem department;

    public Server(Socket socket, int id) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        department = new ReferenceSystem();
        requestHandler = new RequestHandler();
        this.id = id;
    }

    @Override
    public void run() {
        try {

            System.out.println("Client run " + id);
            while (!socket.isClosed()){
                try {
                    //Request request = (Request) in.readObject();

                    JAXBContext jaxbInContext = JAXBContext.newInstance(Request.class);
                    Unmarshaller jaxbInUnmarshaller = jaxbInContext.createUnmarshaller();
                    Request request = (Request)jaxbInUnmarshaller.unmarshal(in);

                    System.out.println(request.getCommand());
                    System.out.println(Arrays.toString(request.getArgs()));

                    requestHandler.setRequest(request);
                    Response response = requestHandler.performRequest();

                    //out.writeObject(responce);

                    JAXBContext jaxbOutContext = JAXBContext.newInstance(Response.class);
                    Marshaller jaxbOutMarshaller = jaxbOutContext.createMarshaller();
                    jaxbOutMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    jaxbOutMarshaller.marshal(response, System.out);
                    jaxbOutMarshaller.marshal(response, out);
                    out.writeObject(null);
                    out.flush();

                } catch (JAXBException | IOException e) {
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
