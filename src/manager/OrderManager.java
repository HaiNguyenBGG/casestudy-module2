package manager;

import storage.impl.OrderDAOImpl;
import model.Order;
import model.Customer;
import model.OrderDetail;
import model.Payment;
import model.Shipping;

import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private final OrderDAOImpl orderDAO = new OrderDAOImpl();

    public OrderManager() {
        this.orders = orderDAO.getAll();
    }

    public void createOrder(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        Order order = new Order(id, customer, orderDetails, orderDate, payment, shipping);
        orders.add(order);
        orderDAO.saveOrders();
        orderDAO.saveOrdersToCSV();
        System.out.println("Đơn hàng đã được tạo thành công!");
    }

    public Order getOrderById(int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    public void deleteOrder(int id) {
        orders.removeIf(o -> o.getId() == id);
        orderDAO.saveOrders();
        orderDAO.saveOrdersToCSV();
        System.out.println("Đơn hàng đã được xóa!");
    }
}
