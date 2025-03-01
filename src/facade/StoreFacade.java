package facade;

import manager.CustomerManager;
import manager.OrderDetailManager;
import manager.OrderManager;
import manager.ProductManager;
import storage.dao.OrderDAO;
import storage.impl.OrderDAOImpl;
import model.*;

import java.util.List;

public class StoreFacade {
    private static StoreFacade instance;
    private final CustomerManager customerManager;
    private final OrderManager orderManager;
    private final OrderDetailManager orderDetailManager;
    private final ProductManager productManager;
    private final OrderDAO orderDAO = new OrderDAOImpl();

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

    public String addCustomer(int id, String name, String email, String phone) {
        return customerManager.addCustomer(id, name, email, phone);
    }

    public String removeCustomer(int id) {
        return customerManager.removeCustomer(id);
    }

    public List<Customer> getAllCustomers() {
        return customerManager.getAllCustomers();
    }

    public boolean customerExists(int id) {
        return customerManager.customerExists(id);
    }

    public Customer getCustomerById(int id) {
        return customerManager.getCustomerById(id);
    }

    public void addProduct(int id, String name, double price, int stock) {
        productManager.addProduct(id, name, price, stock);
    }

    public List<Product> getAllProducts() {
        return productManager.getAllProducts();
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

    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void viewProducts() {
        productManager.viewProducts();
    }

    public void removeProduct(int id) {
        productManager.removeProduct(id);
    }

    public boolean productExists(int id) {
        return productManager.productExists(id);
    }
}
