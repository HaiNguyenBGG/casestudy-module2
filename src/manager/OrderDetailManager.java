package manager;

import storage.impl.OrderDetailDAOImpl;
import model.OrderDetail;
import model.Product;

import java.util.List;

public class OrderDetailManager {
    private List<OrderDetail> orderDetails;
    private final OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();

    public OrderDetailManager() {
        this.orderDetails = orderDetailDAO.getAll();
    }

    public void addOrderDetail(int id, int orderId, Product product, int quantity, double price) {
        OrderDetail orderDetail = new OrderDetail(id, orderId, product, quantity, price);
        orderDetails.add(orderDetail);
        orderDetailDAO.saveOrderDetails();
        orderDetailDAO.saveOrderDetailsToCSV();
        System.out.println("Chi tiết đơn hàng đã được thêm thành công!");
    }

    public OrderDetail getOrderDetailById(int id) {
        return orderDetails.stream().filter(od -> od.getId() == id).findFirst().orElse(null);
    }

    public void viewOrderDetails() {
        if (orderDetails.isEmpty()) {
            System.out.println("Không có chi tiết đơn hàng nào.");
        } else {
            orderDetails.forEach(System.out::println);
        }
    }

    public void deleteOrderDetail(int id) {
        orderDetails.removeIf(od -> od.getId() == id);
        orderDetailDAO.saveOrderDetails();
        orderDetailDAO.saveOrderDetailsToCSV();
        System.out.println("Chi tiết đơn hàng đã được xóa!");
    }
}
