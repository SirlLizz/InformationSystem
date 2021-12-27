package com.example.informationsystem.reference;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.Order;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class Serialization{

    public void serialize (List order, File path){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(order);
            out.close();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
    }

    public List<Order> deserializeOrder (File path){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            List<Order> read = (List<Order>)in.readObject();
            in.close();
            return read;
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Some error occurred!");
        }
        return null;
    }

    public List<Customer> deserializeCustomer (File path) {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
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
