package controller.view;


import com.example.shared.reference.ReferenceSystem;

public class AddCustomerController {

    ReferenceSystem department;

    public AddCustomerController(ReferenceSystem department){
        this.department = department;
    }

    public void addCustomerClick(String fullName, String telephone, String address){
        department.addCustomer(fullName, telephone, address);
    }
}
