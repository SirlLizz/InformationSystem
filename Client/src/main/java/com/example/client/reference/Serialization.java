package com.example.client.reference;

import com.example.shared.model.Customer;
import com.example.shared.model.CustomerList;
import com.example.shared.model.Order;
import com.example.shared.model.OrderList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Serialization {

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
