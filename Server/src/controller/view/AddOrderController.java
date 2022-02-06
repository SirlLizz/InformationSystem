package controller.view;


import com.example.shared.model.Customer;
import com.example.shared.reference.ReferenceSystem;

import java.time.LocalDate;

public class AddOrderController {

    ReferenceSystem department;
    AddCustomerController addCustomerController;

    public AddOrderController(ReferenceSystem department, AddCustomerController addCustomerController){
        this.department = department;
        this.addCustomerController = addCustomerController;
    }

    public void addOrderClick(Customer customer, LocalDate date, double price){
        department.addOrder(customer, date, price);
    }

}
