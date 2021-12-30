package com.example.informationsystem.reference;

import com.example.informationsystem.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

    public List<Order> findDate(ReferenceSystem department, String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<Order>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getOrderDate().toString()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findPrice(ReferenceSystem department, String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<Order>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(String.valueOf(department.getOrders().get(i).getOrderPrice())).find()){
                findOrder.add(department.getOrders().get(i));
                System.out.println(department.getOrders().get(i).toString());
            }
        }
        return findOrder;
    }

    public List<Order> findFullName(ReferenceSystem department, String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<Order>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getCustomer().getName()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findNumber(ReferenceSystem department, String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<Order>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getCustomer().getPhoneNumber()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findAddress(ReferenceSystem department, String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<Order>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getCustomer().getAddress()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }
}
