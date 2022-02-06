package controller.view;

import com.example.shared.model.Customer;
import com.example.shared.reference.ReferenceSystem;

import java.time.LocalDate;

public class ChangeOrderController {

    ReferenceSystem department;
    ChangeCustomerController changeCustomerController;

    public ChangeOrderController(ReferenceSystem department, ChangeCustomerController changeCustomerController){
        this.department = department;
        this.changeCustomerController = changeCustomerController;
    }

    public void changeOrderClick(String orderID, Customer customer, LocalDate date, double price){
        department.changeOrderInformation(orderID, customer, date, price);
    }

}
