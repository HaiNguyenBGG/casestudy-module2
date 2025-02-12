package facade;

import manager.CustomerManager;
import manager.OrderDetailManager;
import manager.OrderManager;
import manager.ProductManager;
import model.*;
import java.util.List;

public class StoreFacade {
    private static StoreFacade instance;
    private final CustomerManager customerManager;
    private final OrderManager orderManager;
    private final OrderDetailManager orderDetailManager;
    private final ProductManager productManager;

    private StoreFacade() {
        this.customerManager = new CustomerManager();
        this.orderManager = new OrderManager();
        this.orderDetailManager = new OrderDetailManager();
        this.productManager = new ProductManager();
    }

    public static synchronized StoreFacade getInstance() {
        if (instance == null) {
            instance = new StoreFacade();
        }
        return instance;
    }

    public void addCustomer(int id, String name, String email, String phone) {
        customerManager.addCustomer(id, name, email, phone);
    }

    public void removeCustomer(int id) {
        customerManager.removeCustomer(id);
    }

    public List<Customer> getAllCustomers() {
        return customerManager.getAllCustomers();
    }

    public void addProduct(int id, String name, double price, int stock) {
        productManager.addProduct(id, name, price, stock);
    }

    public void viewProducts() {
        productManager.viewProducts();
    }

    public void createOrder(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        orderManager.createOrder(id, customer, orderDetails, orderDate, payment, shipping);
    }

    public void removeOrder(int id) {
        orderManager.removeOrder(id);
    }

    public List<Order> getAllOrders() {
        return orderManager.getAllOrders();
    }
}
