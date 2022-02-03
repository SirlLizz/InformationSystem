package controller;

import model.Customer;
import transport.Request;
import transport.Response;
import controller.view.*;
import reference.Find;
import reference.ReferenceSystem;
import reference.Serialization;

import java.io.File;
import java.time.LocalDate;

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
        switch (request.getCommand()) {
            case "/add/customer" -> {
                addCustomerController.addCustomerClick(request.getArgs()[0], request.getArgs()[1], request.getArgs()[2]);
                response = new Response(department);
            }
            case "/add/order" -> {
                addOrderController.addOrderClick(new Customer(request.getArgs()[0], request.getArgs()[1], request.getArgs()[2]), LocalDate.parse(request.getArgs()[3]), Double.parseDouble(request.getArgs()[4]));
                response = new Response(department);
            }
            case "/delete/customer" -> {
                menuController.deleteCustomerClick(Integer.parseInt(request.getArgs()[0]));
                response = new Response(department);
            }
            case "/change/customer" -> {
                changeCustomerController.changeCustomerClick(Integer.parseInt(request.getArgs()[0]), request.getArgs()[1], request.getArgs()[2], request.getArgs()[3]);
                response = new Response(department);
            }
            case "/delete/order" -> {
                menuController.deleteOrderClick(Integer.parseInt(request.getArgs()[0]));
                response = new Response(department);
            }
            case "/change/order" -> {
                changeOrderController.changeOrderClick(Integer.parseInt(request.getArgs()[0]), new Customer(request.getArgs()[1], request.getArgs()[2], request.getArgs()[3]), LocalDate.parse(request.getArgs()[4]), Double.parseDouble(request.getArgs()[5]));
                response = new Response(department);
            }
            case "/save/order", "/save/customer" -> {
                response = new Response(department);
            }
            case "/open/order" -> {
                menuController.openOrder(new File(request.getArgs()[0]));
                response = new Response(department);
            }
            case "/open/add/order" -> {
                menuController.addOpenOrder(new File(request.getArgs()[0]));
                response = new Response(department);
            }
            case "/open/customer" -> {
                menuController.openCustomer(new File(request.getArgs()[0]));
                response = new Response(department);
            }
            case "/open/add/customer" -> {
                menuController.addOpenCustomer(new File(request.getArgs()[0]));
                response = new Response(department);
            }
            case "/find/date" -> {
                response = new Response(new ReferenceSystem());
                response.getDepartment().setOrders(menuController.findDate(request.getArgs()[0]));
            }
            case "/find/price" -> {
                response = new Response(new ReferenceSystem());
                response.getDepartment().setOrders(menuController.findPrice(request.getArgs()[0]));
            }
            case "/find/fullname" -> {
                response = new Response(new ReferenceSystem());
                response.getDepartment().setOrders(menuController.findFullName(request.getArgs()[0]));
            }
            case "/find/number" -> {
                response = new Response(new ReferenceSystem());
                response.getDepartment().setOrders(menuController.findNumber(request.getArgs()[0]));
            }
            case "/find/address" -> {
                response = new Response(new ReferenceSystem());
                response.getDepartment().setOrders(menuController.findAddress(request.getArgs()[0]));
            }
            case "/sort/customer/high" -> {
                menuController.sortCustomerToHigh();
                response = new Response(department);
            }
            case "/sort/customer/low" -> {
                menuController.sortCustomerToLow();
                response = new Response(department);
            }
            case "/sort/order/high" -> {
                menuController.sortOrderToHigh();
                response = new Response(department);
            }
            case "/sort/order/low" -> {
                menuController.sortOrderToLow();
                response = new Response(department);
            }
        }
        return response;
    }

}
