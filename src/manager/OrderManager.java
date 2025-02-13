package manager;

import storage.dao.OrderDAO;
import storage.impl.OrderDAOImpl;
import model.Order;
import model.Customer;
import model.OrderDetail;
import model.Payment;
import model.Shipping;
import storage.utils.LogService;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public OrderManager() {
        this.orders = new ArrayList<>(orderDAO.loadOrders());
    }

    public void createOrder(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        Order order = new Order(id, customer, orderDetails, orderDate, payment, shipping);
        orders.add(order);
        orderDAO.saveOrders(orders);
//        System.out.println("Đơn hàng đã được tạo và lưu vào file.");
    }

    public void removeOrder(int id) {
        Order order = orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
        if (order != null) {
            orders.remove(order);
            orderDAO.saveOrders(orders);

            LogService.log("Đã xóa đơn hàng ID: " + id + " của khách hàng: " + order.getCustomer().getName());
        } else {
            System.out.println("Không tìm thấy đơn hàng!");
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void saveOrders() {
        orderDAO.saveOrders(orders);
    }
}
