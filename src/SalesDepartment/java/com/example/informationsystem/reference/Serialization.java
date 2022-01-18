package com.example.informationsystem.reference;

import com.example.informationsystem.model.Customer;
import com.example.informationsystem.model.CustomerList;
import com.example.informationsystem.model.Order;
import com.example.informationsystem.model.OrderList;

import java.io.*;
import java.util.List;
import javax.xml.bind.*;

public class Serialization{

    public void serializeCustomer (List<Customer> list, File file){
        try{
            CustomerList customers = new CustomerList(list);
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(customers, file);
        }
        catch(JAXBException e ){
            System.out.println(e.getMessage());
        }
    }

    public void serializeOrder(List<Order> list, File file){
        try{
            OrderList orders = new OrderList(list);
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(orders, file);
        }
        catch(JAXBException e ){
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> deserializeCustomer (File file) {
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CustomerList read = (CustomerList)jaxbUnmarshaller.unmarshal(file);
            return read.getCustomers();
        }
        catch(JAXBException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Order> deserializeOrder (File file){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            OrderList read = (OrderList)jaxbUnmarshaller.unmarshal(file);
            return read.getOrders();
        }
        catch(JAXBException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
