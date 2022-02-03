package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "orderList")
@XmlRootElement
public class OrderList implements Serializable {
    private List<Order> orders;

    public  OrderList(){
        orders = new ArrayList<>();
    }

    public  OrderList(List<Order> newOrders){
        orders = newOrders;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
