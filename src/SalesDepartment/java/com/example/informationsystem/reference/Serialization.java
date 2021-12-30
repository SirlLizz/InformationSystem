package com.example.informationsystem.reference;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class Serialization{

    public void serialize (List order, File file){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(order);
            out.close();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
    }

    public List<Order> deserializeOrder (File file){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            List<Order> read = (List<Order>)in.readObject();
            in.close();
            return read;
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Some error occurred!");
        }
        return null;
    }

    public List<Customer> deserializeCustomer (File file) {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            List<Customer> read = (List<Customer>)in.readObject();
            in.close();
            return read;
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Some error occurred!");
        }
        return null;
    }
}
