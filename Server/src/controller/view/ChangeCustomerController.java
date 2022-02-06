package controller.view;


import com.example.shared.reference.ReferenceSystem;

public class ChangeCustomerController {

    ReferenceSystem department;

    public ChangeCustomerController(ReferenceSystem department){
        this.department = department;
    }

    public void changeCustomerClick(String customerID, String fullName, String telephone, String address){
        department.changeCustomerInformation(customerID, fullName, telephone, address);
    }
}
