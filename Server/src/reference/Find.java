package reference;

import com.example.shared.model.Order;
import com.example.shared.reference.ReferenceSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Find {

    ReferenceSystem department;

    public Find(ReferenceSystem department){
        this.department = department;
    }

    public List<Order> findDate(String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getOrderDate().toString()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findPrice(String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(String.valueOf(department.getOrders().get(i).getOrderPrice())).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findFullName(String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getCustomer().getName()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findNumber(String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getCustomer().getPhoneNumber()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }

    public List<Order> findAddress(String patternNew) {
        Pattern pattern = Pattern.compile(patternNew);
        List<Order> findOrder = new ArrayList<>();
        for(int i =0; i<department.getOrders().size();i++){
            if(pattern.matcher(department.getOrders().get(i).getCustomer().getAddress()).find()){
                findOrder.add(department.getOrders().get(i));
            }
        }
        return findOrder;
    }
}
