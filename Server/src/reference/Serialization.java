package reference;

import model.Customer;
import model.CustomerList;
import model.Order;
import model.OrderList;

import javax.xml.bind.*;
import java.io.File;
import java.util.List;

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
