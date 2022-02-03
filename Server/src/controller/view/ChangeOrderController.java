package controller.view;


import model.Customer;
import reference.ReferenceSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChangeOrderController {

    ReferenceSystem department;
    ChangeCustomerController changeCustomerController;

    public ChangeOrderController(ReferenceSystem department, ChangeCustomerController changeCustomerController){
        this.department = department;
        this.changeCustomerController = changeCustomerController;
    }

    public void changeOrderClick(int orderID, Customer customer, LocalDate date, double price){
        department.changeOrderInformation(orderID, customer, date, price);
    }

}
