package com.example.informationsystem;

import com.example.informationsystem.department.Customer;

import java.io.*;

public class Serialization {

    public static void serializeCustomer (Customer customer, String path)throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        try{
            new ObjectOutputStream(out).writeObject(customer);
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }

    }

    public static Customer deserializeCustomer (String path) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        try{
            return (Customer) new ObjectInputStream(in).readObject();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
        return null;
    }
}
