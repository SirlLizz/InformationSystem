package controller;

import transport.Request;
import transport.Response;
import controller.view.*;
import reference.Find;
import reference.ReferenceSystem;
import reference.Serialization;

public class RequestHandler {
    private Request request;
    private Response response;

    //reference
    private ReferenceSystem department = new ReferenceSystem();
    private Find find = new Find(department);
    private Serialization serialization = new Serialization();

    //controller
    private MenuController menuController;
    private AddOrderController addOrderController;
    private AddCustomerController addCustomerController;
    private ChangeOrderController changeOrderController;
    private ChangeCustomerController changeCustomerController;

    public RequestHandler() {
        addCustomerController = new AddCustomerController(department);
        addOrderController = new AddOrderController(department, addCustomerController);
        changeCustomerController = new ChangeCustomerController(department);
        changeOrderController = new ChangeOrderController(department, changeCustomerController);
        menuController = new MenuController(department, serialization, find, addOrderController, addCustomerController, changeOrderController, changeCustomerController);
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response performRequest(){
        switch (request.getCommand()){
            case "/add/customer":
                addCustomerController.addCustomerClick(request.getArgs()[0], request.getArgs()[1], request.getArgs()[2]);
                break;
        }
        response = new Response(department);
        return response;
    }

}
