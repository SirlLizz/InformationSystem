package controller.view;


import model.Customer;
import reference.ReferenceSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddOrderController {

    ReferenceSystem department;
    AddCustomerController addCustomerController;

    public AddOrderController(ReferenceSystem department, AddCustomerController addCustomerController){
        this.department = department;
        this.addCustomerController = addCustomerController;
    }

    public void addCustomerClick(){
        /*
        AddCustomerView addCust = new AddCustomerView(department, addCustomerController);
        addCust.showStage();
         */
    }

    public void addOrderClick(Customer customer, LocalDate date, double price){
        department.addOrder(customer, date, price);
    }

    public Customer actionComboBox(String comboValue){
        Customer customer = null;
        for (int i = 0; i < department.getCustomers().size(); i++) {
            if (department.getCustomers().get(i).getName().equals(comboValue)) {
                customer = department.getCustomers().get(i);
            }
        }
        return customer;
    }
    public List<String> showComboBox(){
        List<String> names = new ArrayList<>();
        for (int i = 0; i < department.getCustomers().size(); i++){
            names.add(department.getCustomers().get(i).getName());
        }
        return names;
    }

    public boolean checkPriceToDouble(String price) {
        try {
            Double priceDouble = Double.parseDouble(price);
            return true;
        } catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkCustomer(Customer customer) {
        return customer != null;
    }
}
