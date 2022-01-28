package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "customer")
@XmlRootElement
public class Customer{
    static private int nextID = 0;

    @XmlAttribute(name = "ID")
    private final int customerID;
    private String name;
    private String phoneNumber;
    private String address;

    public Customer(){
        this.customerID = 0;
        this.address = null;
        this.name = null;
        this.phoneNumber = null;
    }

    public Customer(String name, String phoneNumber, String address){
        customerID = nextID++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String newAddress){
        address = newAddress;
    }

    public int getCustomerID(){
        return customerID;
    }

    public static void setNextID(int newID){
        nextID = newID;
    }

    public String toString(){
        return "ID: " + customerID + "; Name: " + name + "; Phone: " + phoneNumber + "; Address: " + address;
    }

}
