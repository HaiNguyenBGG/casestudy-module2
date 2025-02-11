package manager;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();

    public void createOrder(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        Order order = new Order(id, customer, orderDetails, orderDate, payment, shipping);
        orders.add(order);
        System.out.println("Đơn hàng đã được tạo thành công!");
    }

    public Order getOrderById(int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void deleteOrder(int id) {
        orders.removeIf(o -> o.getId() == id);
        System.out.println("Đơn hàng đã được xóa!");
    }
}
