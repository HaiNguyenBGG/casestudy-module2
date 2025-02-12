package manager;

import storage.dao.OrderDetailDAO;
import storage.impl.OrderDetailDAOImpl;
import model.OrderDetail;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailManager {
    private final List<OrderDetail> orderDetails;
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    public OrderDetailManager() {
        this.orderDetails = new ArrayList<>(orderDetailDAO.loadOrderDetails());
    }

    public void addOrderDetail(int id, Order order, Product product, int quantity) {
        OrderDetail orderDetail = new OrderDetail(id, order, product, quantity);
        order.addOrderDetail(orderDetail);
        orderDetails.add(orderDetail);
        orderDetailDAO.saveOrderDetails(orderDetails);
        System.out.println("Đã thêm chi tiết đơn hàng và cập nhật tổng giá trị đơn hàng.");
    }

    public void removeOrderDetail(int id) {
        OrderDetail orderDetail = orderDetails.stream()
                .filter(od -> od.getId() == id)
                .findFirst()
                .orElse(null);

        if (orderDetail != null) {
            orderDetail.getOrder().removeOrderDetail(id);
            orderDetails.remove(orderDetail);
            orderDetailDAO.saveOrderDetails(orderDetails);
            System.out.println("Chi tiết đơn hàng đã được xóa và tổng giá trị cập nhật.");
        } else {
            System.out.println("Không tìm thấy chi tiết đơn hàng.");
        }
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetails;
    }
}
