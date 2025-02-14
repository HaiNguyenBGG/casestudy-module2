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
import java.util.Optional;

public class OrderManager {
    private List<Order> orders;
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public OrderManager() {
        this.orders = new ArrayList<>(orderDAO.loadOrders());
    }

    public void createOrder(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        if (orders.stream().anyMatch(o -> o.getId() == id)) {
            System.out.println("ID đơn hàng đã tồn tại! Hãy nhập ID khác.");
            return;
        }

        Order order = new Order(id, customer, orderDetails, orderDate, payment, shipping);
        orders.add(order);
        orderDAO.saveOrders(orders);
        LogService.log("Đã tạo đơn hàng ID: " + id + " | Khách: " + customer.getName());
    }


    public void removeOrder(int id) {
        Optional<Order> orderOpt = orders.stream().filter(o -> o.getId() == id).findFirst();
        if (orderOpt.isPresent()) {
            orders.remove(orderOpt.get());
            orderDAO.saveOrders(orders);
            LogService.log("🗑Đã xóa đơn hàng ID: " + id);
        } else {
            System.out.println("Không tìm thấy đơn hàng!");
        }
    }

    public void updateOrder(Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == updatedOrder.getId()) {
                orders.set(i, updatedOrder);
                orderDAO.saveOrders(orders);
                LogService.log("Đã cập nhật đơn hàng ID: " + updatedOrder.getId());
                return;
            }
        }
        System.out.println("Không tìm thấy đơn hàng cần cập nhật!");
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void saveOrders() {
        orderDAO.saveOrders(orders);
    }
}
