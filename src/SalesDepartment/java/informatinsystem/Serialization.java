package informatinsystem;

import informatinsystem.department.Customer;
import informatinsystem.department.Order;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class Serialization{

    public static void serializeCustomer (List<Customer> customer){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Customer", "*.cust"));
        Stage stage = new Stage();
        File path = chooser.showSaveDialog(stage);
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(customer);
            out.close();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
    }

    public static List<Customer> deserializeCustomer () throws ClassNotFoundException{
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Customer", "*.cust"));
        Stage stage = new Stage();
        File path = chooser.showOpenDialog(stage);
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            return (List<Customer>) in.readObject();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
        return null;
    }

    public static void serializeOrder(List<Order> order){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Order", "*.ord"));
        Stage stage = new Stage();
        File path = chooser.showSaveDialog(stage);
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(order);
            out.close();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
    }

    public static List<Order> deserializeOrder () throws ClassNotFoundException{
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Order", "*.ord"));
        Stage stage = new Stage();
        File path = chooser.showOpenDialog(stage);
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            return (List<Order>) in.readObject();
        }
        catch(IOException e){
            System.out.println("Some error occurred!");
        }
        return null;
    }
}
